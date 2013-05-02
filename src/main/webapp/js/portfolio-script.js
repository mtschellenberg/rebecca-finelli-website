var modalIds = new Array();

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
        modal += createModalEntry(index + 1, large, portfolio.title[index],
                portfolio.caption[index]);
        modalIds[index] = createModalId(index + 1);
    })
    table += "</div>\n";
    $("#table").html(table);
    $("#modal").html(modal);
    resizeModals($(window).height());
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

function createModalEntry(index, image, title, caption) {
    return "<div id=\"" + createModalId(index) + "\" class=\"modal hide fade modal-custom\" tabindex=\"-1\" aria-labelledby=\"modalLabel" + index + "\" aria-hidden=\"true\">\n" + createModalTitle(index, title) + "<div class=\"modal-body\">\n" + createImage(image) + "</div>\n" + createModalCaption(caption) + "</div>\n";
}

function createModalId(index) {
    return "image" + index + "Modal";
}

function createModalTitle(index, title) {
    return "<div class=\"modal-header\">\n<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n<h4 id=\"modalLabel" + index + "\">" + title + "</h4>\n</div>\n";
}

function createModalCaption(caption) {
    return "<div class=\"modal-footer\">\n" + caption + "\n</div>\n";
}

function resizeModals(windowHeight) {
    $.each(modalIds, function(index, id) {
        var position = Math.max(10, (windowHeight - $("#" + id).height()) / 2);
        $("#" + id).css("top", position + "px");
    });
}

$(window).resize(function() {
    resizeModals($(window).height());
});

