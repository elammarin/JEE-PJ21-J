function drawPoint(context, x, y, label, color, size) {
    if (color == null) {
        color = '#000';
    }
    if (size == null) {
        size = 5;
    }

    var radius = 0.5 * size;

    // to increase smoothing for numbers with decimal part
    var pointX = Math.round(x - radius);
    var pointY = Math.round(y - radius);

    context.beginPath();
    context.fillStyle = color;
    context.fillRect(pointX, pointY, size, size);
    context.fill();

    if (label) {
        var textX = Math.round(x);
        var textY = Math.round(pointY - 5);

        context.font = 'Italic 14px Arial';
        context.fillStyle = color;
        context.textAlign = 'center';
        context.fillText(label, textX, textY);
    }
}


$(function() {

    // Usage example:

    const canvas = document.getElementById("canvas");
    console.log(canvas)
    const context = canvas.getContext('2d');

    const erreur= $("#error");


    $.ajax({
        method: 'GET',
        url: `./list.json`,
    }).done((resData)=> {
        console.log(resData)
        resData.map(dk=>{
            drawPoint(context, dk.x, dk.y, dk.numDocker, 'black', 5);
        })

    })

    $(".remove").click( function(e) {

       const id = $(this).data("id");

        $.ajax({
                method: 'DELETE',
                url: `./remove/${id}.json`,
            }).done((resData)=> {
                console.log(resData)
                location.reload();
            })



    });

    $("#form-add").submit( function(e) {

        e.preventDefault();
        const data= {}
        const values = $(this).serializeArray();
        values.map(v=>{data[v['name']]=v['value']});
        $.ajax({
            method: 'POST',
            url: `./post.json`,
            dataType: "json",
            contentType: 'application/json',
            data:JSON.stringify(data )
        }).done((resData)=> {

            location.reload();
        }).fail(()=>{
            erreur.css("display","block");
            erreur.html("Quai existe déja");
        })



    });

});
