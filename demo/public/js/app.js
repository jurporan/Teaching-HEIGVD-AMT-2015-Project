(function() {
    var appModule = angular.module("garyDemo", [])
        .factory("progressBarValues", function() {
            var current = 0;
            var max = 300;
            var level = 1;

            return {
                current,
                max,
                level
            };
        })
        .factory("badgesValues", function() {
            return {
                "x1": [
                    {
                        "id": 1,
                        locked: true,
                        "title": "Title1",
                        "text": "test1"
                    },
                    {
                        "id": 2,
                        locked: false,
                        "title": "First answer!",
                        "text": "You successfully posted your first answer!"
                    },
                    {
                        "id": 3,
                        locked: true,
                        "title": "Title2",
                        "text": "test2"
                    },
                    {
                        "id": 4,
                        locked: true,
                        "title": "Title3",
                        "text": "test3"
                    },
                    {
                        "id": 5,
                        locked: true,
                        "title": "Title4",
                        "text": "test4"
                    }
                ]
            };
        })
        .controller("PageController", function($scope, progressBarValues, badgesValues) {
            $scope.showProject = [];

            // This will be used for concurrency issues.
            var levelLoading = false;

            /*
             * Add points to the level's progress bar.
             * Parameters:
             *  - extraPoints: inficate if there is extra points to add to the progress
             *                 bar. This value will be different to 0 if this is a
             *                 recursive call, indicating that there was too many points
             *                 added in the previous level.
             *                 More explainations in the function.
             */
            function addPoints(extraPoints = 0) {
                // First check if the function isn't currently executed (or is executed but
                // recursively), then check if the input text is a positive number, otherwise
                // we just ignore it.
                if ((!levelLoading || extraPoints > 0) && !isNaN($scope.txtEventProperties && $scope.txtEventProperties > 0)) {
                    levelLoading = true;
                    // Will store the current progress bar's points or the extra
                    // points.
                    var tmpCurrent;

                    // If there is more than 0 extra point, we currently are in a recursive
                    // call of this function. That means that the number of added points
                    // is too big for only one level so we'll have extra points for the next
                    // one. In this case we need to continue the progression of the ar after
                    // the first level up.
                    if (extraPoints > 0) {
                        tmpCurrent = extraPoints;
                        console.log("Still " + tmpCurrent + " points to add...");
                        console.log("Points to next level : " + progressBarValues.max + ".")
                    }
                    // Otherwise we need to add the number filled by the user in the event
                    // properties' field.
                    else {
                        tmpCurrent = progressBarValues.current + parseInt($scope.txtEventProperties);
                    }

                    // If the new points value if lesser than the maximum points value of
                    // the current level, we just add them to the progress bar and end the
                    // function.
                    if ((tmpCurrent < progressBarValues.max)) {
                        progressBarValues.current = tmpCurrent;
                        levelLoading = false;
                    }
                    // Otherwise if there is more points than the maximum points value of
                    // the current level, we'll have to recursively call this function to
                    // add the extra points to the next level.
                    // NB : If there is still too many points for the next level, the
                    // function will be called recursively again, etc.
                    else {
                        // First entirely fill the progress bar in its content, to show
                        // the user he's going to level up.
                        progressBarValues.current = progressBarValues.max;
                        console.log("NEXT LEVEL!");

                        // Wait for the end of the progress bar's transition to the maximum
                        // number of points.
                        $("#progressBarCurrent").one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function() {
                            // Temporary remove transitions on the progress bar, for resetting
                            // it properly, then fade it out.
                            $("#progressBarCurrent").addClass("notransition");
                            $("#progressBarCurrent").fadeOut("fast", function() {
                                // Reset the progress bar's width.
                                progressBarValues.current = 0;
                                // Manually apply the scope changings, because no user action will
                                // trigger it automatically.
                                $scope.$apply();
                            // Fade the progress bar in after a delay of 300 ms, and then put back
                            // transitions on it.
                            }).delay(300).fadeIn("fast", function() {
                                $("#progressBarCurrent").removeClass("notransition");
                                // Calculate the next level's extra points.
                                var extraPoints = tmpCurrent - progressBarValues.max;
                                // Level up and set new level's maximum points value.
                                progressBarValues.level++;
                                progressBarValues.max += 100;
                                // Set the new progress bar's value ; if there is too many
                                // points for the level, just entirely fill it in the container.
                                // A recursive call will be executed just below.
                                progressBarValues.current = Math.min(extraPoints, progressBarValues.max);
                                // Apply manually the scope changings, because no user action will trigger it.
                                $scope.$apply();

                                // If there is still too many points for the current level, recursively
                                // call the function to consider them.
                                if (extraPoints >= progressBarValues.max - 100) {
                                    addPoints(extraPoints);
                                }

                                levelLoading = false;
                            });
                        });
                    }
                }
            }

            function addBadge() {
                // First check than the input text is a positive number, otherwise
                // we just ignore it.
                if (!isNaN($scope.txtEventProperties && $scope.txtEventProperties > 0)) {
                    $.each(badgesValues, function(text, value) {
                        $.each(value, function() {
                            if (this.id == $scope.txtEventProperties) {
                                $scope.progressBarPercentage = 50;
                                $scope.showProject[this.id] = true;
                                $scope.$apply();
                                return false;
                            }
                        });
                    });
                }
            }

            /*
             * Process an event, depending on the user's selected event.
             * Triggered when the user clicked on the "Run!" button.
             */
            $scope.showGamification = function() {
                $("#fillForm").fadeOut("fast", function() {
                    $("#resultsTabs").fadeIn("fast", function() {


                    });
                });

                if ($scope.eventSelect == 456) {
                    addPoints();
                }
                else if ($scope.eventSelect == 654) {
                    addBadge();
                }

                $('html, body').animate({
                    scrollTop: $("#form").offset().top
                }, 800);

            };
        })
        .controller("ProgressController", function($scope, progressBarValues) {
            $scope.$watchCollection(function() { return [progressBarValues.current, progressBarValues.max, progressBarValues.level] }, function (newValues, oldValues) {
                $scope.level = newValues[2];
                $scope.numberOfPoints = newValues[0];
                $scope.pointsToNextLevel = newValues[1];
                $scope.progressBarPercentage = newValues[0] / (newValues[1] / 100);
            });
        })
        .controller("BadgesController", function($scope, badgesValues) {
            $scope.badgesArchitecture = badgesValues;
        });
})();
