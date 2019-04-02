
function deleteExpense(id) {
    if (!window.confirm("Are you sure?")) {
        e.preventDefault();
    } else {
        $.ajax({
            url: '/deleteExpense/' + id,
            success: function () {
                alert('You have deleted the expense !');
                window.location.reload();
            }
        })
    }
}

function fillForm(id) {
    $.ajax({
        url: '/editExpense/' + id,
        success: function (data) {
            $("#editModalContent").append(data);
        }
    })
}

function cleanForm() {
    $("#editModalContent").empty();
}

window.setTimeout(function () {
    $("#alert-success").fadeTo(500, 0).slideUp(500, function () {
        $(this).remove();
    });
}, 3000);