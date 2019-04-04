function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function drawPie(obj, divId, title) {
    let parsedJSON = JSON.parse(obj);
    console.log(parsedJSON);
    var rows = [];
    for (var i = 0; i < parsedJSON.length; i++) {
        var row = {};
        row['label'] = capitalizeFirstLetter(parsedJSON[i].name.toLocaleLowerCase());
        row['value'] = parsedJSON[i].value;
        rows.push(row);
    }
    var pie = new d3pie(divId, {
        "header": {
            "title": {
                "text": title,
                "fontSize": 24,
                "font": "open sans"
            },
            "subtitle": {
                "text": "A full pie chart to show off label collision detection and resolution.",
                "color": "#999999",
                "fontSize": 12,
                "font": "open sans"
            },
            "titleSubtitlePadding": 9
        },
        "footer": {
            "color": "#999999",
            "fontSize": 10,
            "font": "open sans",
            "location": "bottom-left"
        },
        "size": {
            "canvasHeight": 400,
            "canvasWidth": 590,
            "pieOuterRadius": "90%"
        },
        "data": {
            "sortOrder": "value-desc",
            "content": rows
        },
        "labels": {
            "outer": {
                "pieDistance": 32
            },
            "inner": {
                "hideWhenLessThanPercentage": 3
            },
            "mainLabel": {
                "fontSize": 14
            },
            "percentage": {
                "color": "#ffffff",
                "decimalPlaces": 0
            },
            "value": {
                "color": "#adadad",
                "fontSize": 14
            },
            "lines": {
                "enabled": true
            },
            "truncation": {
                "enabled": true
            }
        },
        "effects": {
            "pullOutSegmentOnClick": {
                "effect": "linear",
                "speed": 400,
                "size": 8
            }
        },
        "misc": {
            "gradient": {
                "enabled": true,
                "percentage": 100
            }
        }
    });
}