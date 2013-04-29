jQuery.post("./Portfolio/getPortfolio", function(data) {
    var portfolio = jQuery.parseJSON(data);
    var table = "<div class=\"row\">\n";
    var modal = "";
    $.each(portfolio.small, function(index, small) {
        if(index != 0 && index % 5 == 0) {
            table += createRow();
        }
        table += createTableEntry(index + 1, small);
    })
    $.each(portfolio.large, function(index, large) {
        var i = index + 1;
        var title = "Image " + i;
        modal += createModalEntry(index + 1, large, title);
    })
    table += "</div>\n";
    $("#table").html(table);
    $("#modal").html(modal);
});

function createRow() {
    return "</div>\n<div class=\"row\">\n";
}

function createTableEntry(index, image) {
    return "<div class=\"span2 padded\">\n<a href=\"#image" + index + "Modal\" data-toggle=\"modal\">\n" + createImage(image) + "</a>\n</div>\n";
}

function createImage(image) {
    return "<img src=\"" + image + "\">\n";
}

function createModalEntry(index, image, title) {
    return "<div id=\"image" + index + "Modal\" class=\"modal hide fade\" tabindex=\"-1\" aria-labelledby=\"modalLabel" + index + "\" aria-hidden=\"true\">\n" + createModalTitle(index, title) + "<div class=\"modal-body\">\n" + createImage(image) + "</div>\n</div>\n";
}
        
function createModalTitle(index, title) {
    return "<div class=\"modal-header\">\n<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times</button>\n<h5 id=\"modalLabel" + index + "\">" + title + "</h5>\n</div>\n";
}
