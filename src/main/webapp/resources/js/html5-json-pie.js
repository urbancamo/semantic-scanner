(function($){

    //get total value
    var getTotal = function(x){
        var t = 0;
        
        $.each(x, function(i, d) {
            t += parseFloat(d.value);
        });
        
        return t;
    };
    
    $.fn.pieChart = function(data, settings) {
        
            // Config settings
        var defaults = {  
            chartSizePercent:55,                       
            sliceBorderWidth:1,                         
            sliceBorderStyle:"#fff",                   
            sliceGradientColour:"#ddd",                 
            maxPullOutDistance:25,                     
            pullOutFrameStep:4,                         
            pullOutFrameInterval:40,                   
            pullOutLabelPadding:65,                    
            pullOutLabelFont:"bold 16px 'Ubuntu', Verdana, sans-serif",  
            pullOutValueFont:"bold 12px 'Ubuntu', Verdana, sans-serif",  
            pullOutValuePrefix:"",                     
            pullOutShadowColour:"rgba( 0, 0, 0, .5 )",  
            pullOutShadowOffsetX:5,                    
            pullOutShadowOffsetY:5,                     
            pullOutShadowBlur:5,                        
            pullOutBorderWidth:1,                       
            pullOutBorderStyle:"#333"
        };
        
        $.extend(defaults, settings);
        
        return this.each(function(){

            var canvas = this,
                currentPullOutSlice = -1,
                currentPullOutDistance = 0,
                animationId = 0,
                totalValue = getTotal(data),
                startPullOut = function(canvas, slice, d){

                    // Exit if we're already pulling out this slice
                    if ( currentPullOutSlice == slice ) return;

                    // Record the slice that we're pulling out, clear any previous animation, then start the animation
                    currentPullOutSlice = slice;
                    currentPullOutDistance = 0;
                    clearInterval( animationId );
                    
                    animationId = setInterval(function(){ 
                        animatePullOut(canvas, slice, d); 
                    }, defaults.pullOutFrameInterval);

                },
                toggleSlice = function(canvas, slice, d) {
                    if(slice == currentPullOutSlice ) {
                        pushIn(canvas, d);
                    }else{
                        startPullOut(canvas, slice, d);
                    }
                },
                pushIn = function(canvas, d) {
                    currentPullOutSlice = -1;
                    currentPullOutDistance = 0;
                    clearInterval( animationId );
                    drawChart(d, canvas);

                },
                drawChart = function(d, canvas) {

                    context = canvas.getContext('2d');
                    
                    context.clearRect(0, 0, canvas.width, canvas.height);

                    for(var slice in d){
                        if(slice != currentPullOutSlice){ 
                            drawSlice(canvas, d, context, slice);
                        }
                    }

                    if(currentPullOutSlice != -1 ){ 
                        drawSlice(canvas, d, context, currentPullOutSlice);
                    }

                },
                drawSlice = function(canvas, d, context, slice) {
            
                    var centreX = canvas.width / 2,
                    centreY = canvas.height / 2,
                    chartRadius = Math.min( canvas.width, canvas.height ) / 2 * ( defaults.chartSizePercent / 100 ),
                    chartStartAngle = -.5 * Math.PI;              // Start the chart at 12 o'clock instead of 3 o'clock;
                
                    var s = d[slice],
                        // Compute the adjusted start and end angles for the slice
                        startAngle = s['startAngle']  + chartStartAngle,
                        endAngle = s['endAngle']  + chartStartAngle,

                        easeOut = function(ratio, power){
                            return ( Math.pow ( 1 - ratio, power ) + 1 );
                        };

                    if(slice == currentPullOutSlice) {
                    
                        var midAngle = (startAngle + endAngle) / 2,
                            actualPullOutDistance = currentPullOutDistance * easeOut( currentPullOutDistance/defaults.maxPullOutDistance, .8 );
                        
                        startX = centreX + Math.cos(midAngle) * actualPullOutDistance;
                        startY = centreY + Math.sin(midAngle) * actualPullOutDistance;

                        context.fillStyle = 'rgb(' + s.color.join(',') + ')';
                        context.textAlign = "center";
                        context.font = defaults.pullOutLabelFont;
                        context.fillText(s['label'], centreX + Math.cos(midAngle) * ( chartRadius + defaults.maxPullOutDistance + defaults.pullOutLabelPadding ), centreY + Math.sin(midAngle) * ( chartRadius + defaults.maxPullOutDistance + defaults.pullOutLabelPadding ) );
                        context.font = defaults.pullOutValueFont;
                        context.fillText( defaults.pullOutValuePrefix + s['value'] + " (" + ( parseInt( s['value'] / totalValue * 100 + .5 ) ) +  "%)", centreX + Math.cos(midAngle) * ( chartRadius + defaults.maxPullOutDistance + defaults.pullOutLabelPadding ), centreY + Math.sin(midAngle) * ( chartRadius + defaults.maxPullOutDistance + defaults.pullOutLabelPadding ) + 20 );
                        context.shadowOffsetX = defaults.pullOutShadowOffsetX;
                        context.shadowOffsetY = defaults.pullOutShadowOffsetY;
                        context.shadowBlur = defaults.pullOutShadowBlur;

                    } else {

                        // This slice isn't pulled out, so draw it from the pie centre
                        startX = centreX;
                        startY = centreY;
                    }
                    
                    
                    // Set up the gradient fill for the slice
                    var sliceGradient = context.createLinearGradient( 0, 0, canvas.width*.75, canvas.height*.75 );
                    
                    sliceGradient.addColorStop( 0, defaults.sliceGradientColour );
                    sliceGradient.addColorStop( 1, 'rgb(' + s.color.join(',') + ')' );

                    // Draw the slice
                    context.beginPath();
                    context.moveTo( startX, startY );
                    context.arc(startX, startY, chartRadius, startAngle, endAngle, false);
                    context.lineTo( startX, startY );
                    context.closePath();
                    context.fillStyle = sliceGradient;
                    context.shadowColor = ( slice == currentPullOutSlice ) ? defaults.pullOutShadowColour : "rgba( 0, 0, 0, 0 )";
                    context.fill();
                    context.shadowColor = "rgba( 0, 0, 0, 0 )";

                    // Style the slice border appropriately
                    if(slice == currentPullOutSlice){
                        context.lineWidth = defaults.pullOutBorderWidth;
                        context.strokeStyle = defaults.pullOutBorderStyle;
                    } else {
                        context.lineWidth = defaults.sliceBorderWidth;
                        context.strokeStyle = defaults.sliceBorderStyle;
                    }

                    // Draw the slice border
                    context.stroke();
                },
                animatePullOut = function(canvas, slice, d){

                    // Pull the slice out some more
                    currentPullOutDistance += defaults.pullOutFrameStep;

                    // If we've pulled it right out, stop animating
                    if ( currentPullOutDistance >= defaults.maxPullOutDistance ) {
                        clearInterval( animationId );
                        return;
                    }

                    // Draw the frame
                    drawChart(d, canvas);
                },
                addAngles = function(d){
                    var currentPos = 0; // The current position of the slice in the pie (from 0 to 1)

                    for(var slice in d) {
                      d[slice]['startAngle'] = 2 * Math.PI * currentPos;
                      d[slice]['endAngle'] = 2 * Math.PI * ( currentPos + ( d[slice]['value'] / totalValue ) );
                      currentPos += d[slice]['value'] / totalValue;
                    }
                    
                    return d;

                },
                bindPieClicks = function(e, d){

                    var centreX = e.width / 2,
                        centreY = e.height / 2,
                        chartRadius = Math.min( e.width, e.height ) / 2 * ( defaults.chartSizePercent / 100 );
                    
                    $(e).click(function(clickEvent){

                        var chartStartAngle = -.5 * Math.PI,
                            mouseX = clickEvent.pageX - this.offsetLeft,
                            mouseY = clickEvent.pageY - this.offsetTop,
                            xFromCentre = mouseX - centreX,
                            yFromCentre = mouseY - centreY,
                            distanceFromCentre = Math.sqrt( Math.pow( Math.abs( xFromCentre ), 2 ) + Math.pow( Math.abs( yFromCentre ), 2 ) );

                            if(distanceFromCentre <= chartRadius){

                                var clickAngle = Math.atan2( yFromCentre, xFromCentre ) - chartStartAngle;
                                if ( clickAngle < 0 ) clickAngle = 2 * Math.PI + clickAngle;

                                for(var slice in d){
                                    if (clickAngle >= d[slice]['startAngle'] && clickAngle <= d[slice]['endAngle'] ) {

                                        // Slice found. Pull it out or push it in, as required.
                                        toggleSlice(e, slice, d);

                                        return;
                                    }
                                }
                            }

                        pushIn(e, d);
                    });
                };
                
            
            // Exit if the browser isn't canvas-capable
            if(typeof canvas.getContext === 'undefined'){
                return;
            }

            data = addAngles(data); //add angles to the data chart
            drawChart(data, canvas); //draw chart
            bindPieClicks(canvas, data); //bind pie clicks
            
        });
    };
    
})(jQuery);