function drawCharts(categoryObj, monthObj, yearObj){
    let parsedCategories = JSON.parse(categoryObj);
    var rowsCategories = [];
    for (var i = 0; i < parsedCategories.length; i++) {
        rowsCategories[parsedCategories[i].name] = parsedCategories[i].use;
    }
    var dataForCategories = rowsCategories;


    let parsedMonths = JSON.parse(monthObj);
    var rowsMonths = [];
    for (var i = 0; i < parsedMonths.length; i++) {
        rowsMonths[parsedMonths[i].name] = parsedMonths[i].use;
    }
    var dataForMonths = rowsMonths;


    let parsedYears = JSON.parse(yearObj);
    var rowsYears = [];
    for (var i = 0; i < parsedYears.length; i++) {
        rowsYears[parsedYears[i].name] = parsedYears[i].use;
    }
    var dataForYears = rowsYears;

    var width = 300
    height = 300
    margin = 40

    // The radius of the pieplot is half the width or half the height (smallest one). I substract a bit of margin.
    var radius = Math.min(width, height) / 2 - margin

    // append the svg object to the div called 'my_dataviz'
    var svg = d3.select("#my_dataviz")
        .append("svg")
        .attr("width", width)
        .attr("height", height)
        .append("g")
        .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");
    var svg1 = d3.select("#my_dataviz1")
        .append("svg")
        .attr("width", width)
        .attr("height", height)
        .append("g")
        .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");
    var svg2 = d3.select("#my_dataviz2")
        .append("svg")
        .attr("width", width)
        .attr("height", height)
        .append("g")
        .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");


    // set the color scale
    var color1 = d3.scaleOrdinal()
        .domain(dataForCategories)
        .range(d3.schemeSet2);
    var color2 = d3.scaleOrdinal()
        .domain(dataForMonths)
        .range(d3.schemeSet2);
    var color3 = d3.scaleOrdinal()
        .domain(dataForYears)
        .range(d3.schemeSet2);

    // Compute the position of each group on the pie:
    var pie = d3.pie()
        .value(function (d) {
            return d.value;
        })
    var data_ready1 = pie(d3.entries(dataForCategories))
    var data_ready2 = pie(d3.entries(dataForMonths))
    var data_ready3 = pie(d3.entries(dataForYears))
    // Now I know that group A goes from 0 degrees to x degrees and so on.

    // shape helper to build arcs:
    var arcGenerator = d3.arc()
        .innerRadius(0)
        .outerRadius(radius)

    // Build the pie chart: Basically, each part of the pie is a path that we build using the arc function.
    svg
        .selectAll('mySlices')
        .data(data_ready1)
        .enter()
        .append('path')
        .attr('d', arcGenerator)
        .attr('fill', function (d) {
            return (color1(d.data.key))
        })
        .attr("stroke", "black")
        .style("stroke-width", "2px")
        .style("opacity", 0.7)

    // Now add the annotation. Use the centroid method to get the best coordinates
    svg
        .selectAll('mySlices')
        .data(data_ready1)
        .enter()
        .append('text')
        .text(function (d) {
            return d.data.value == 0 ? "" : d.data.key;
        })
        .attr("transform", function (d) {
            return "translate(" + arcGenerator.centroid(d) + ")";
        })
        .style("text-anchor", "middle")
        .style("font-size", 13)

    svg1
        .selectAll('mySlices')
        .data(data_ready2)
        .enter()
        .append('path')
        .attr('d', arcGenerator)
        .attr('fill', function (d) {
            return (color2(d.data.key))
        })
        .attr("stroke", "black")
        .style("stroke-width", "2px")
        .style("opacity", 0.7)

    // Now add the annotation. Use the centroid method to get the best coordinates
    svg1
        .selectAll('mySlices')
        .data(data_ready2)
        .enter()
        .append('text')
        .text(function (d) {
            return d.data.value == 0 ? "" : d.data.key;
        })
        .attr("transform", function (d) {
            return "translate(" + arcGenerator.centroid(d) + ")";
        })
        .style("text-anchor", "middle")
        .style("font-size", 13)
    svg2
        .selectAll('mySlices')
        .data(data_ready3)
        .enter()
        .append('path')
        .attr('d', arcGenerator)
        .attr('fill', function (d) {
            return (color3(d.data.key))
        })
        .attr("stroke", "black")
        .style("stroke-width", "2px")
        .style("opacity", 0.7)

    // Now add the annotation. Use the centroid method to get the best coordinates
    svg2
        .selectAll('mySlices')
        .data(data_ready3)
        .enter()
        .append('text')
        .text(function (d) {
            return d.data.value == 0 ? "" : d.data.key;
        })
        .attr("transform", function (d) {
            return "translate(" + arcGenerator.centroid(d) + ")";
        })
        .style("text-anchor", "middle")
        .style("font-size", 13)
}