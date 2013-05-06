var modalIds = new Array();

jQuery.post("./Portfolio/getPortfolio", function(data) {

    var portfolio = jQuery.parseJSON(data);
    var table = "<div class=\"row\">\n";
    var modal = "";
    var rows = 1;

    $.each(portfolio.small, function(index, small) {
        console.log("Thumbnail:  " + small + ".");

        if(index != 0 && index % 5 == 0) {
            table += createRow();
            ++rows;
        }

        table += createTableEntry(index + 1, small);
    });

    table += "</div>\n";

    $.each(portfolio.large, function(index, large) {
        console.log("Picture:  " + large + ".");

        if(large.indexOf(".swf") > 0) {
            modal += createModalEntry(index + 1, "", portfolio.title[index],
                    portfolio.caption[index]);
        }

        else {
            modal += createModalEntry(index + 1, large, portfolio.title[index],
                    portfolio.caption[index]);
        }

        modalIds[index] = createModalId(index + 1);
    });

    console.log("Modal.\n" + modal + "End Modal.");

    $("#table").html(table);
    $("#modal").html(modal);

    $.each(portfolio.large, function(index, large) {

        if(large.indexOf(".swf") > 0) {
            swfobject.embedSWF(large, createModalBodyId(index + 1), "450",
                    "300", "6.0.65");
        }
    });

    centerModals($("body").height(), $(window).height());
});

function createRow() {
    return "</div>\n<div class=\"row\">\n";
}

function createTableEntry(index, image) {
    return "<div class=\"span2 padded\">\n<a href=\"#" + createModalId(index) + "\" data-toggle=\"modal\">\n" + createImage(image) + "</a>\n</div>\n";
}

function createImage(image) {
    return "<img src=\"" + image + "\" onload=\"centerModalsOnLoad();\">\n";
}

function createModalEntry(index, image, title, caption) {
    return "<div id=\"" + createModalId(index) + "\" class=\"modal hide fade modal-custom\" tabindex=\"-1\" aria-labelledby=\"modalLabel" + index + "\" aria-hidden=\"true\">\n" + createModalTitle(index, title) + "<div class=\"modal-body\">\n" + createModalBody(index, image) + "</div>\n" + createModalCaption(caption) + "</div>\n";
}

function createModalId(index) {
    return "modalImage" + index;
}

function createModalTitle(index, title) {
    return "<div class=\"modal-header\">\n<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n<h4 id=\"modalLabel" + index + "\">" + title + "</h4>\n</div>\n";
}

function createModalBody(index, image) {
    if(image === "") {
        return "<div id=\"" + createModalBodyId(index) + "\"></div>\n";
    }
    return createImage(image);
}

function createModalBodyId(index) {
    return "modalBody" + index;
}

function createModalCaption(caption) {
    return "<div class=\"modal-footer\">\n" + caption + "\n</div>\n";
}

function centerModalsOnLoad() {
    centerModals($("body").height(), $(window).height());
}

function centerModals(bodyHeight, windowHeight) {
    var chosenHeight = Math.min(bodyHeight, windowHeight);
    var padding = 10;
    $.each(modalIds, function(index, id) {
        var top = Math.max(padding, (chosenHeight - $("#" + id).height()) / 2);
        $("#" + id).css("top", top + "px");
    });
}

$(window).resize(function() {
    centerModals($("body").height(), $(window).height());
});
