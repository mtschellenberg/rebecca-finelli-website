window.onload = function () {

    var ImageMap = function (map) {

        var i;
        var areas = map.getElementsByTagName('area');
        var numberOfAreas = areas.length;
        var coordinates = [];

        // Set to the original width of the image.
        var previousWidth = 2725;

        for(i = 0; i < numberOfAreas; ++i) {
            coordinates[i] = areas[i].coords.split(',');
        }

        this.resize = function() {

            var i, j;
            var lengthRatio = document.body.clientWidth / previousWidth;

            for(i = 0; i < numberOfAreas; ++i) {

                var numberOfCoordinates = coordinates[i].length;

                for(j = 0; j < numberOfCoordinates; ++j) {
                    coordinates[i][j] *= lengthRatio;
                }

                areas[i].coords = coordinates[i].join(',');
            }

            previousWidth = document.body.clientWidth;

            return true;
        };

        $(window).resize(this.resize);
    };

    var imageMap = new ImageMap(document.getElementById('nav-map'));

    imageMap.resize();

    return;
}

