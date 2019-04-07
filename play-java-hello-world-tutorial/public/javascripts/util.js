
function deleteExpense(id) {
    if (!e) var e = window.event;
    e.cancelBubble = true;
    if (e.stopPropagation) e.stopPropagation();

    if (!window.confirm("Are you sure?")) {
        e.preventDefault();
    } else {
        $.ajax({
            url: '/deleteExpense/' + id,
            success: function () {
                alert('You have deleted the expense !');
                location.href = '/transactions';
            }
        })
    }
}

function reloadEditMap(long, lat) {
    setTimeout(()=>{
        var features = markerSourceEdit.getFeatures();
        features.forEach((feature) => {
            markerSourceEdit.removeFeature(feature);
        });

        var iconFeatureEditsEditTwo = [];

        var iconFeatureEditTwo = new ol.Feature({
            geometry: new ol.geom.Point(ol.proj.transform([long, lat], 'EPSG:4326',
                'EPSG:3857')),
            name: 'Null Island',
            population: 4000,
            rainfall: 500
        });

        markerSourceEdit.addFeature(iconFeatureEditTwo);
        mapEdit.updateSize();
        },
    500
);}

function fillForm(id, long, lat){
    if (!e) var e = window.event;
    e.cancelBubble = true;
    if (e.stopPropagation) e.stopPropagation();
    $('#exampleModalEdit').modal('show');

    $.ajax({
            url: '/editExpense/' + id,
            success: function (data) {
                $("#editModalContent").append(data);
            }
        });
        reloadEditMap(long, lat);
}

function cleanForm() {
    $("#editModalContent").empty();
}

window.setTimeout(function () {
    $("#alert-success").fadeTo(500, 0).slideUp(500, function () {
        $(this).remove();
    });
}, 3000);

function reloadPage(){
    location.href = '/transactions';
    location.reload();
    var featuresExpense = markerSourceExpense.getFeatures();
    featuresExpense.forEach((feature) => {
        markerSourceExpense.removeFeature(feature);
    });

    var featuresIncome = markerSourceIncome.getFeatures();
    featuresExpense.forEach((feature) => {
        markerSourceIncome.removeFeature(feature);
    });

    var featuresEdit = markerSourceEdit.getFeatures();
    featuresEdit.forEach((feature) => {
        markerSourceEdit.removeFeature(feature);
});
    location.href = '/transactions';
}
