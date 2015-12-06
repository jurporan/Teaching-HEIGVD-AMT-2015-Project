(function() {
    // Static application's API key.
    var apiKey = "de9b55a9-bcac-4cb0-8fcc-0e736b175813";

    var appModule = angular.module("garyDemo", [])
        // Returns users' data.
        .factory("usersData", function() {
            var data = [];

            data["123"] = "Blairôme";
            data["132"] = "edri"
            data["321"] = "Jean-Mich'";

            return data;
        })
        // Returns level progression's bar's values.
        .factory("progressBarValues", function() {
            // Current progression.
            var current = 0;
            // Progression-to-next-level.
            var max = 300;
            // Current level.
            var level = 1;

            return {
                current,
                max,
                level
            };
        })
        // Returns badges data.
        .factory("badgesValues", function() {
            // We can have more than one line ; just indicate a new line with "x2", etc.
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
        // Returns leaderboard scores.
        .factory("leaderboardScores", function() {
            var scores = [
                {
                    "userId": 123,
                    "username": "Blairôme",
                    "points": 100
                },
                {
                    "userId": 132,
                    "username": "edri",
                    "points": 0
                },
                {
                    "userId": 321,
                    "username": "Jean-Mich'",
                    "points": 60
                }
            ];

            return scores;
        })
        // The main page's controller.
        // Contains functions which allow the user to add points and badges.
        .controller("PageController", function($scope, $http, usersData, progressBarValues, badgesValues, leaderboardScores) {
            $scope.usersData = usersData;
            // This will contain all user's badges' status (loccked/unlocked).
            $scope.showBadge = [];
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
                    // properties' field and update the user's score in the database.
                    else {
                        tmpCurrent = progressBarValues.current + parseInt($scope.txtEventProperties);

                        $.each(leaderboardScores, function(score) {
                            if (this.userId == $scope.userSelect) {
                                this.points += parseInt($scope.txtEventProperties);
                                return false;
                            }
                        });
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
                    //      function will be called recursively again, etc.
                    else {
                        // First entirely fill the progress bar in its content, to show
                        // the user he's going to level up.
                        progressBarValues.current = progressBarValues.max;
                        console.log("NEXT LEVEL!");

                        // Behaviour is different, depending on the progress bar's visibility.
                        // If we try to fadeOut a hidden element, we'll have some issues...
                        if($("#progressBarCurrent").is(":visible")) {
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
                        else {
                            // Calculate the next level's extra points.
                            var extraPoints = tmpCurrent - progressBarValues.max;
                            // Level up and set new level's maximum points value.
                            progressBarValues.level++;
                            progressBarValues.max += 100;
                            // Set the new progress bar's value ; if there is too many
                            // points for the level, just entirely fill it in the container.
                            // A recursive call will be executed just below.
                            progressBarValues.current = Math.min(extraPoints, progressBarValues.max);

                            // If there is still too many points for the current level, recursively
                            // call the function to consider them.
                            if (extraPoints >= progressBarValues.max - 100) {
                                addPoints(extraPoints);
                            }

                            levelLoading = false;
                        }
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
                                $scope.showBadge[this.id] = true;
                                $scope.$apply();
                                return false;
                            }
                        });
                    });
                }
            }

            $scope.userChanging = function() {
                console.log("New selected user: " + $scope.userSelect);

                console.log("Get user's statistics...")
                $http.get('http://localhost:8080/Gary/api/applications/' + $scope.apiKey + '/users/' + $scope.userSelect + '/reputation')
                    .then(
                        function success(response) {
                            console.log("Current points: " + response.data.points);
                            console.log("Current badges: " + JSON.stringify(response.data.badges));                            
                            progressBarValues.current = parseInt(response.data.points);
                        },
                        function error(response) {
                            console.log("An error occured, please retry.");
                        }
                    );
            }

            /*
             * Process an event, depending on the user's selected event.
             * Triggered when the user clicked on the "Run!" button.
             */
            $scope.showGamification = function() {
                // Hide information panel and show the results one.
                $("#fillForm").hide();
                $("#resultsTabs").fadeIn("fast");
                // Scroll to results if we are above the form.
                if ($("#form").offset().top > $(window).scrollTop()) {
                    $('html, body').animate({
                        scrollTop: $("#form").offset().top
                    }, 800);
                }

                // Add points or a badge, depending on the user event's selection.
                if ($scope.eventSelect == 456) {
                    addPoints();
                }
                else if ($scope.eventSelect == 654) {
                    addBadge();
                }

                // Sort scores (greater on top).
                leaderboardScores.sort(function(score1, score2) {
                    return parseInt(score2.points) - parseInt(score1.points);
                });
                // Send back leaderboard's scores to view.
                $scope.leaderboardScores = leaderboardScores;
            };
        })
        // Controller relative to the progress bar's rendering.
        .controller("ProgressController", function($scope, progressBarValues) {
            // Watchs every progress bar's parameter ; if one of them changes, enters the callback function.
            $scope.$watchCollection(function() { return [progressBarValues.current, progressBarValues.max, progressBarValues.level]; }, function (newValues, oldValues) {
                // Set new scope's values.
                $scope.level = newValues[2];
                $scope.numberOfPoints = newValues[0];
                $scope.pointsToNextLevel = newValues[1];
                $scope.progressBarPercentage = newValues[0] / (newValues[1] / 100);
            });
        })
        // Controller relative to the badges' table's rendering.
        .controller("BadgesController", function($scope, badgesValues) {
            // Load badges data.
            $scope.badgesArchitecture = badgesValues;
        })
        // Controller relative to the leaderboard's table's rendering.
        .controller("LeaderboardController", function($scope, leaderboardScores) {
            $scope.watch(function() {return leaderboardScores.points; }, function(newValue, oldValue) {
                // Sort scores (greater on top).
                leaderboardScores.sort(function(score1, score2) {
                    return parseInt(score2.points) - parseInt(score1.points);
                });
                // Send back leaderboard's scores to view.
                $scope.leaderboardScores = leaderboardScores;
            });
        });
})();
