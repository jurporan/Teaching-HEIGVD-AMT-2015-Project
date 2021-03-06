(function() {
    var appModule = angular.module("garyDemo", [])
        // Used to display duplicates rules only one time in the select.
        .filter('uniqueRule', function() {
            return function(collection, keyname) {
                var output = [],
                    typesOfEvent = [];

                // Only get one time each rule identified by keyname.
                angular.forEach(collection, function(item) {
                    var typeOfEvent = item[keyname];
                    if(typesOfEvent.indexOf(typeOfEvent) == -1) {
                        typesOfEvent.push(typeOfEvent);
                        output.push(item);
                    }
                });

                return output;
            };
        })
        // Will allow us to acces an uploaded file object in our controllers.
        // In the snippet below, file-model is an attribute on a file input
        // element, and its value is the name of the variable in our controller's
        // scope that binds to the file object.
        .directive('fileModel', ['$parse', function ($parse) {
            return {
                restrict: 'A',
                link: function(scope, element, attrs) {
                    var model = $parse(attrs.fileModel);
                    var modelSetter = model.assign;

                    element.bind('change', function() {
                        scope.$apply(function(){
                            modelSetter(scope, element[0].files[0]);
                        });
                    });
                }
            };
        }])
        // This service upload the given file to the given URL and return a
        // promise.
        .service('fileUpload', ['$http', '$q', function ($http, $q) {
            this.uploadFileToUrl = function(file, uploadUrl) {
                var defer = $q.defer();

                // Get file's extension.
                var parts = file.name.split('.');
                var extension = parts[parts.length - 1].toLowerCase();

                // Check file's extension.
                if (extension != "gif" && extension != "jpeg" && extension != "jpg" && extension != "png") {
                    defer.reject("wrongExtension");
                }
                else {
                    // Create form data's object and add the uploaded file.
                    var fd = new FormData();
                    fd.append('file', file);

                    $http.post(uploadUrl, fd, {
                        transformRequest: angular.identity,
                        headers: {'Content-Type': undefined}
                    }).then(
                        function success(response) {
                            defer.resolve(response.data);
                        },
                        function error(response) {
                            defer.reject("uploadFailed");
                        }
                    );
                }

                return defer.promise;
            }
        }])
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
            // The array is filled in the 'BadgesController' controller.
            return {
                "x1": []
            };
        })
        // Returns leaderboard scores.
        .factory("leaderboardScores", function() {
            var scores = [
                {
                    "userId": 123,
                    "username": "Blairôme",
                    "points": 0
                },
                {
                    "userId": 132,
                    "username": "edri",
                    "points": 0
                },
                {
                    "userId": 321,
                    "username": "Jean-Mich'",
                    "points": 0
                }
            ];

            return scores;
        })
        // The main page's controller.
        // Contains functions which allow the user to add points and badges.
        .controller("PageController", function($scope, $http, $filter, usersData, progressBarValues, badgesValues, leaderboardScores) {
            $scope.usersData = usersData;
            // This will contain all user's badges' status (loccked/unlocked).
            $scope.showBadge = [];
            // This will be used for concurrency issues.
            var levelLoading = false;

            /*
             * Add points to the level's progress bar.
             * Parameters:
             *  - points: points to add to the progress bar.
             *  - extraPoints: indicate if there is extra points to add to the progress
             *                 bar. This value will be different to 0 if this is a
             *                 recursive call, indicating that there was too many points
             *                 added in the previous level.
             *                 More explainations in the function.
             */
            function addPoints(points, extraPoints) {
                // First check if the function isn't currently executed (or is executed but
                // recursively), then check if the input text is a positive number, otherwise
                // we just ignore it.
                if ((!levelLoading || extraPoints >= 0) || (!isNaN(points) && points > 0)) {
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
                        tmpCurrent = progressBarValues.current + parseInt(points);

                        angular.forEach(leaderboardScores, function(score) {
                            if (score.userId == $scope.userSelect) {
                                score.points += parseInt(points);
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
                                    $("#newLevel").addClass("newLevelBig");
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
                                        addPoints(0, extraPoints);
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
                                addPoints(0, extraPoints);
                            }

                            levelLoading = false;
                        }
                    }
                }
            }

            // Search for the given badge's ID and unlock it.
            function addBadge(id) {
                angular.forEach(badgesValues, function(badges, line) {
                    angular.forEach(badges, function(badge) {
                        if (badge.id === id) {
                            if (!$scope.showBadge[id]) {
                                $("#explosion" + id).addClass("explosionBig");
                            }

                            $scope.showBadge[id] = true;
                            console.log("\"" + badge.name + "\" badge unlocked!");
                            return false;
                        }
                    });
                });
            }

            $scope.userChanging = function() {
                console.log("New selected user: " + $scope.userSelect);

                // Reset user's badges, except the "Welcome" one.
                console.log("Clear badges...");
                angular.forEach(badgesValues, function(badges, line) {
                    angular.forEach(badges, function(badge) {
                        if (badge.name != "Welcome") {
                            $scope.showBadge[badge.id] = false;
                        }
                    });
                });

                console.log("Get every user's statistics...")
                angular.forEach($scope.users, function(user) {
                    $http.get($scope.restApiServerAddress + '/Gary/api/applications/' + $scope.apiKey + '/users/' + user.id + '/reputation')
                        .then(
                            function success(response) {
                                // Search for the user in the leaderboard.
                                angular.forEach(leaderboardScores, function(score) {
                                    // Add points to the user, unless he is the
                                    // selected one.
                                    if (score.userId == user.id && user.id != $scope.userSelect) {
                                        score.points = response.data.points;
                                        return false;
                                    }
                                });

                                // If current user is the selected one, process data.
                                if (user.id == $scope.userSelect) {
                                    // Hide error panel if shown.
                                    $("#optionsError").fadeOut("fast");
                                    console.log("Current points: " + response.data.points);
                                    console.log("Current badges: " + JSON.stringify(response.data.badges));

                                    // Adds selected user's current points.
                                    addPoints(parseInt(response.data.points));
                                    // Adds selected user's current badges.
                                    angular.forEach(response.data.badges, function(badge) {
                                        addBadge(badge.id);
                                    });
                                }
                            },
                            function error(response) {
                                // Set and show error panel.
                                $("#optionsErrorContent").text("I could not load this user's statistics, please retry.");
                                $("#optionsError").fadeIn("fast");
                            }
                        );
                });
            }

            /*
             * Process an event, depending on the user's selected event.
             * Triggered when the user clicked on the "Run!" button.
             */
            $scope.showGamification = function() {
                // If set, the event's properties must be an integer.
                if (!$scope.txtEventProperties || ($scope.txtEventProperties && !isNaN($scope.txtEventProperties))) {
                    $("#userSelect").attr("disabled", "disabled");

                    // Hide error panel if shown.
                    $("#optionsError").fadeOut("fast");
                    // Hide information panel and show the results one.
                    $("#fillForm").hide();
                    $("#resultsTabs").fadeIn("fast");
                    // Scroll to results if we are above the form.
                    if ($("#form").offset().top > $(window).scrollTop()) {
                        $('html, body').animate({
                            scrollTop: $("#form").offset().top
                        }, 800);
                    }

                    // Create new user's event.
                    var event = {
                        "type": $scope.eventSelect,
                        "parameter": ($scope.txtEventProperties ? $scope.txtEventProperties : null)
                    };

                    console.log("Post new user's event...");
                    $http.post($scope.restApiServerAddress + '/Gary/api/applications/' + $scope.apiKey + '/users/' + $scope.userSelect + '/events', event)
                        .then(
                            function success(response) {
                                // Search for the rules associated with the selected event.
                                // Add points and badges.
                                angular.forEach($scope.rules, function(rule) {
                                    if (rule.typeOfEvent == $scope.eventSelect && !rule.penalty) {
                                        // New points to add
                                        if (rule.rewardType == 1) {
                                            console.log("Add " + rule.ruleParameter + " points...");
                                            addPoints(rule.ruleParameter, 0);
                                        }
                                        else if (rule.rewardType == 2) {
                                            // Search for the rule's badge in badges array.
                                            var badge = $filter('filter')($scope.badges, function(b) {
                                                return b.id === rule.ruleParameter;
                                            });

                                            addBadge(badge[0].id);
                                        }
                                    }
                                });
                            },
                            function error(response) {
                                // Set and show error panel.
                                $("#optionsErrorContent").text("Sorry I could not post that event, please retry.");
                                $("#optionsError").fadeIn("fast");
                            }
                        );
                }
                else {
                    // Set and show error panel.
                    $("#optionsErrorContent").text("The event's properties must be an integer.");
                    $("#optionsError").fadeIn("fast");
                }
            };
        })
        // Controller relative to the progress bar's rendering.
        .controller("ProgressController", function($scope, $http, progressBarValues, badgesValues) {
            // Watchs every progress bar's parameter ; if one of them changes, enters the callback function.
            $scope.$watchCollection(function() { return [progressBarValues.current, progressBarValues.max, progressBarValues.level]; }, function (newValues, oldValues) {
                // Set new scope's values.
                $scope.level = newValues[2];
                $scope.numberOfPoints = newValues[0];
                $scope.pointsToNextLevel = newValues[1];
                $scope.progressBarPercentage = newValues[0] / (newValues[1] / 100);

                if ($scope.level == 3) {
                    // Create new user's event.
                    var event = {
                        "type": "Level 3",
                        "parameter": null
                    };

                    console.log("Post new event...");
                    $http.post($scope.restApiServerAddress + '/Gary/api/applications/' + $scope.apiKey + '/users/' + $scope.userSelect + '/events', event)
                        .then(
                            function success(response) {
                                angular.forEach(badgesValues, function(badges, line) {
                                    angular.forEach(badges, function(badge) {
                                        if (badge.name == "Level 3!") {
                                            $scope.showBadge[badge.id] = true;
                                            console.log("\"" + "Level 3!" + "\" badge unlocked!");
                                            return false;
                                        }
                                    });
                                });
                            },
                            function error(response) {
                                console.log("An error occured when trying to unlock 'Level 3' badge.");
                            }
                        );
                }
            });
        })
        // Controller relative to the badges' table's rendering.
        .controller("BadgesController", function($scope, badgesValues) {
            var i = 0;
            // Fill the badges' array.
            angular.forEach($scope.badges, function(badge) {
                // Every badge is locked by default, except the "Welcome" one.
                if (badge.name == "Welcome") {
                    badge.locked = false;
                }
                else {
                    badge.locked = true;
                }

                // Create a new line each 5 badges.
                if (i % 5 === 0) {
                    badgesValues['x' + (Math.floor(i / 5) + 1)] = [];
                }

                // 5 badges per table's line.
                badgesValues['x' + (Math.floor(i / 5) + 1)][i % 5] = badge;
                ++i;
            });

            // Load badges data.
            $scope.badgesArchitecture = badgesValues;
        })
        // Controller relative to the leaderboard's table's rendering.
        .controller("LeaderboardController", function($scope, leaderboardScores) {
            // Recursively watch leaderboard's scores.
            $scope.$watch(function() { return leaderboardScores; }, function (newValue, oldValue) {
                // Sort scores (greater on top).
                leaderboardScores.sort(function(score1, score2) {
                    return parseInt(score2.points) - parseInt(score1.points);
                });
                // Send back leaderboard's scores to view.
                $scope.leaderboardScores = leaderboardScores;
            }, true);
        })
        // Controller relative to the badge adding panel.
        .controller("ManageRulesController", function($scope, $http, $filter, fileUpload) {
            // Indicates if a badge is currently selected by the user ; used for
            // form validation.
            $scope.isBadgeSelected = false;
            // By default the rule is not a penalty.
            $scope.penalty = false;
            // Indicates if the user successfully managed an event or not.
            $scope.successPosting = false;
            // Will contain the new added badge's ID if the user added one.
            $scope.newBadgeId = null;
            // Indicates whether the "Execute!" button and the "Rule type" select
            // are disabled or not.
            $scope.btnExecuteDisabled = true;
            $scope.ruleTypeDisabled = true;

            // Contains all actions ; used by the actions list.
            $scope.actionsOptions = [
                {
                    "name": "Add my own rule!",
                    "value": "add"
                },
                {
                    "name": "Edit a rule to beautify it",
                    "value": "edit"
                },
                {
                    "name": "Delete an ugly and not-beloved rule",
                    "value": "delete"
                }
            ];
            // Contains all rules' types ; used by the rules' types list.
            $scope.ruleTypesOptions = [
                {
                    "name": "A rule with points",
                    "value": "points"
                },
                {
                    "name": "A rule with a badge",
                    "value": "badge"
                }
            ];
            // Contains all badge's kinds ; used by the badges kinds list.
            $scope.badgeKindOptions = [
                {
                    "name": "A whole new badge!",
                    "value": "add"
                },
                {
                    "name": "An already existing one",
                    "value": "existing"
                }
            ];

            // Cancel a badge adding by reseting data.
            $scope.cancelBadgeAdding = function(resetKindSelection) {
                // If the user selected a logo for his badge, it has been uploaded.
                // Because of the cancelation we need to delete it so we won't have
                // useless files on the server.
                if ($scope.badgeImageUrl != null && $scope.badgeImageUrl != "default.png") {
                    // Send a request to the node server to delete the file.
                    $http.post("/cancelupload", {"fileName": $scope.badgeImageUrl});
                }

                $scope.badgeName = null;
                $scope.badgeImageUrl = "default.png";
                $scope.badgeDescription = null;
                $scope.badgeAddingError = null;
                $scope.isBadgeSelected = false;

                if (resetKindSelection) {
                    $scope.badgeKindSelect = null;
                }
            };

            // Shows the right action's panel, depending on the user's choice of
            // action.
            $scope.$watch("actionSelect", function(newValue, oldValue) {
                if (newValue) {
                    // Reset badge adding panel.
                    $scope.cancelBadgeAdding(true);
                    $("#addBadgePanel").css("visibility", "hidden");
                    hideBadgeAdding(false);

                    // Reset data.
                    isBadgeSelected = false;
                    $scope.ruleSelect = null;
                    $scope.ruleName = null;
                    $scope.ruleType = null;
                    $scope.numberOfPoints = null;
                    $scope.badgeSelect = null;
                    $scope.minValue = null;
                    $scope.maxValue = null;
                    $scope.penalty = false;
                    $scope.error = null;
                    $scope.btnExecuteDisabled = true;
                    // Hides all elements.
                    $("#addRuleOption, #selectRuleOption, #deleteRuleOption, #addRuleOption, \
                        #newBadgeValue, #badgePreviewPanel, #rulePoints, #ruleBadge, #existingBadge, \
                        #minValue, #maxValue, #penalty").hide();

                    // Then shows useful elements, depending on the user's choice.
                    if (newValue === "add") {
                        $("#addRuleOption").slideDown("fast");
                    }
                    else if (newValue === "edit" || newValue === "delete") {
                        $("#selectRuleOption").slideDown("fast");
                    }
                }
            });

            // Load selected rule's details when the user select a rule to edit.
            $scope.loadSelectedRule = function() {
                if ($scope.ruleSelect) {
                    // Reset badge adding panel.
                    $scope.cancelBadgeAdding(true);
                    $("#addBadgePanel").css("visibility", "hidden");
                    hideBadgeAdding(false);

                    // Slides down rule's details only if the user wants to edit a rule.
                    if ($scope.actionSelect === "edit") {
                        $("#addRuleOption").slideDown("fast");

                        $scope.ruleName = $scope.ruleSelect.typeOfEvent;

                        // Shows and fill right fields, depending on the event's reward type.
                        // 1. The user selected a points event.
                        if ($scope.ruleSelect.rewardType == 1) {
                            $scope.ruleType = $scope.ruleTypesOptions[0].value;
                            $scope.numberOfPoints = $scope.ruleSelect.ruleParameter;
                        }
                        // 2. The user selected a badge event.
                        else if ($scope.ruleSelect.rewardType == 2) {
                            $scope.ruleType = $scope.ruleTypesOptions[1].value;
                            $scope.badgeKindSelect = $scope.badgeKindOptions[1].value;

                            // Search for the rule's badge in badges array.
                            var badge = $filter('filter')($scope.badges, function(b) {
                                return b.id === $scope.ruleSelect.ruleParameter;
                            });

                            $scope.badgeSelect = badge[0];
                        }

                        $scope.minValue = $scope.ruleSelect.minValue;
                        $scope.maxValue = $scope.ruleSelect.maxValue;
                        $scope.penalty = $scope.ruleSelect.penalty;
                    }
                    // Otherwise enables the 'Execute!' button.
                    else if ($scope.actionSelect === "delete") {
                        $scope.btnExecuteDisabled = false;
                    }
                }
            };

            // Enables the rule's type selection when the user entered a value
            // for the name.
            $scope.$watch("ruleName", function(newValue, oldValue) {
                if (newValue) {
                    $scope.ruleTypeDisabled = false;
                }
                else {
                    $scope.ruleTypeDisabled = true;
                    $scope.btnExecuteDisabled = true;
                }
            });

            // Shows the points or badge inputs, depending on the user's choice
            // of rule's type.
            $scope.$watch("ruleType", function(newValue, oldValue) {
                if (newValue) {
                    // Reset badge adding panel.
                    $scope.cancelBadgeAdding(true);
                    $("#addBadgePanel").css("visibility", "hidden");
                    hideBadgeAdding(false);
                    isBadgeSelected = false;

                    if (newValue == "points") {
                        $("#ruleBadge, #existingBadge").hide();
                        $("#rulePoints, #minValue, #maxValue, #penalty").fadeIn("fast");
                    }
                    else if (newValue == "badge") {
                        $("#rulePoints").hide();
                        $("#ruleBadge").fadeIn("fast");
                        $("#penalty, #minValue, #maxValue").hide();
                        $scope.numberOfPoints = null;
                        $scope.minValue = null;
                        $scope.maxValue = null;
                    }

                    $("#newBadgeValue").hide();
                    $("#badgePreviewPanel").hide();
                    // Update penalty checkbox's text value.
                    $scope.dataType = newValue;
                    $scope.btnExecuteDisabled = true;
                }
            });

            // Enables the 'Execute' button when the user sets the number of
            // points.
            $scope.$watch("numberOfPoints", function(newValue, oldValue) {
                if (newValue) {
                    $scope.btnExecuteDisabled = false;
                }
                else {
                    $scope.btnExecuteDisabled = true;
                }
            });

            // Cancel a badge adding and shows the badge adding panel if the
            // users choosed to add a new one.
            $scope.$watch("badgeKindSelect", function(newValue, oldValue) {
                if (newValue) {
                    $scope.cancelBadgeAdding(false);
                    $scope.badgeSelect = null;
                    $scope.penalty = false;

                    if (newValue == "add") {
                        $("#badgeLogo").val("");
                        $("#badgePreviewPanel").fadeIn("fast");
                        $("#previewTitle").fadeIn("fast");
                        $("#existingBadge").hide();
                        $("#minValue, #maxValue, #penalty").hide();
                        // Shows badge adding's panel ; this is a rendering JQuery
                        // function located in the rules.jade file.
                        showBadgeAdding();
                    }
                    else if (newValue == "existing") {
                        // Hides badge adding's panel ; this is a rendering JQuery
                        // function located in the rules.jade file.
                        hideBadgeAdding(false);

                        $("#existingBadge").fadeIn("fast");
                        $("#minValue, #maxValue, #penalty").fadeIn("fast");
                    }

                    $("#newBadgeValue").hide();
                    $scope.btnExecuteDisabled = true;
                }
            });

            // Update new badge's preview by the fields values, then shows the
            // badge preview panel when the user chooses a badge in the list.
            $scope.$watch("badgeSelect", function(newValue, oldValue) {
                if (newValue) {
                    $scope.badgeName = $scope.badgeSelect.name;
                    $scope.badgeImageUrl = $scope.badgeSelect.imageUrl;
                    $scope.badgeDescription = $scope.badgeSelect.description;
                    $scope.isBadgeSelected = true;

                    $("#badgePreviewPanel").fadeIn("fast");
                    $scope.btnExecuteDisabled = false;
                }
            });

            // Upload the selected badge's logo by sending a HTTP request to the
            // server.
            $scope.uploadFile = function(files) {
                // If the user already choosed a logo for the current badge,
                // delete the old one so we won't have useless files on the server.
                if ($scope.badgeImageUrl != null && $scope.badgeImageUrl != "default.png") {
                    // Send a request to the node server to delete the file.
                    $http.post("/cancelupload", {"fileName": $scope.badgeImageUrl});
                }

                var file = files[0];
                var uploadUrl = '/upload';
                // Uploads the file with the 'fileUpload' service, and receives
                // back a promises triggered when the HTTP upload request ends.
                var uploadedFile = fileUpload.uploadFileToUrl(file, uploadUrl);

                // Do actions, depending on the file uploading's status.
                uploadedFile.then(
                    // Successful upload.
                    function(answer) {
                        $scope.badgeImageUrl = answer;
                        $scope.badgeAddingError = null;
                        $scope.wrongFile = false;
                    },
                    // Upload failed.
                    function(reason) {
                        if (reason == "wrongExtension") {
                            $scope.badgeAddingError = "Please select a 'gif', 'jp(e)g' or 'png' image.";
                            $scope.wrongFile = true;
                        }
                        else {
                            $scope.badgeAddingError = "An error occured when uploading the file, please retry in a while.";
                        }
                    }
                );
            }

            // Add the given badge to the database via a POST request to the REST
            // API.
            $scope.addBadge = function() {
                $scope.btnExecuteDisabled = true;

                // Fields must be filled.
                if (!$scope.wrongFile) {
                    if ($scope.badgeName && $scope.badgeDescription) {
                        $scope.badgeAddingError = null;

                        // Get badge's data.
                        var badgeData = {
                            name: $scope.badgeName,
                            description: $scope.badgeDescription,
                            imageUrl: ($scope.badgeImageUrl ? $scope.badgeImageUrl : "default.png")
                        };

                        // Then post it.
                        $http.post($scope.restApiServerAddress + '/Gary/api/applications/' + $scope.apiKey + '/badges', badgeData)
                            .then(
                                function success(response) {
                                    if (response.status == 200) {
                                        hideBadgeAdding(false);

                                        // Get badge's ID and push it at the end of the
                                        // badges array so it appears in the badges
                                        // list.
                                        badgeData.id = $scope.newBadgeId = parseInt(response.data);
                                        var newBadge = $scope.badges.push(badgeData);

                                        $scope.newBadgeValue = "New badge's name: " + badgeData.name;
                                        // Indicates a badge has been selected.
                                        $scope.isBadgeSelected = true;

                                        $("#previewTitle").hide();
                                        $("#newBadgeValue, #minValue, #maxValue").fadeIn("fast");
                                        $scope.btnExecuteDisabled = false;
                                    }
                                    else {
                                        $scope.badgeAddingError = "An error occured, please retry.";
                                        $scope.btnExecuteDisabled = false;
                                        $scope.isBadgeSelected = false;
                                    }
                                },
                                function error(response) {
                                    $scope.badgeAddingError = "An error occured, please retry.";
                                    $scope.btnExecuteDisabled = false;
                                    $scope.isBadgeSelected = false;
                                }
                            );
                    }
                    else {
                        $scope.badgeAddingError = "Please fill all fields...";
                        $scope.isBadgeSelected = false;
                        $scope.btnExecuteDisabled = false;
                    }
                }
            };

            // Validates each rule-adding fields and post the new rule.
            $scope.executeAction = function() {
                $scope.btnExecuteDisabled = true;
                $scope.error = null;
                // Initializes HTTP request's server URL.
                var postUrl = $scope.restApiServerAddress + '/Gary/api/applications/' + $scope.apiKey + '/rules';
                // Will contain the HTTP request's method.
                var method;
                // Indicates if the fields are correctly filled by the user.
                var areFieldsValid = false;
                // Will contain rule's data ; used when the user want to add or
                // edit a rule.
                var ruleData = null;

                // Update HTTP request's server URL and method, depending on the user's action.
                if ($scope.actionSelect === $scope.actionsOptions[0].value) {
                    method = "POST";
                }
                else if ($scope.actionSelect === $scope.actionsOptions[1].value) {
                    postUrl += '/' + $scope.ruleSelect.id;
                    method = "PUT";
                }
                else if ($scope.actionSelect === $scope.actionsOptions[2].value) {
                    postUrl += '/' + $scope.ruleSelect.id;
                    method = "DELETE";

                    areFieldsValid = confirm("Are you sure to delete this rule?");
                }

                // Validates fields if the user choosed the "Add rule" or "Edit rule" actions.
                if ($scope.actionSelect === $scope.actionsOptions[0].value || $scope.actionSelect === $scope.actionsOptions[1].value) {
                    // Fields must be filled.
                    if ($scope.ruleName && (($scope.ruleType == "points" && $scope.numberOfPoints) || ($scope.ruleType == "badge" && $scope.isBadgeSelected))) {
                        // If the user choosed the "points" rule's type, the number
                        // of points must be an integer value, greater than 0.
                        if ($scope.ruleType == "points" && (isNaN($scope.numberOfPoints) || parseInt($scope.numberOfPoints) <= 0 || $scope.numberOfPoints % 1 != 0)) {
                            $scope.error = "Please enter an integer value greater than 0 for the points number.";
                        }
                        else {
                            // If min and/or max values are filled, they must be numbers.
                            if ((($scope.minValue || $scope.minValue == undefined) && (isNaN($scope.minValue) || $scope.minValue % 1 != 0)) ||
                                (($scope.maxValue || $scope.maxValue == undefined) && (isNaN($scope.maxValue) || $scope.maxValue % 1 != 0))) {
                                $scope.error = "Please enter an integer value for the minimum and maximum properties values.";
                            }
                            else {
                                // The max must be greater or equals to the min if set.
                                if ($scope.minValue && $scope.maxValue && parseInt($scope.minValue) > parseInt($scope.maxValue)) {
                                    $scope.error = "Maximum properties value must be greater or equals to minimum value.";
                                }
                                else {
                                    // Create the rule's fields, depending on the users values.
                                    ruleData = {
                                        "typeOfEvent": $scope.ruleName,
                                        "ruleParameter": ($scope.ruleType == "points" ? $scope.numberOfPoints : ($scope.badgeKindSelect == $scope.badgeKindOptions[0].value ? $scope.newBadgeId : $scope.badgeSelect.id)),
                                        "penalty": $scope.penalty,
                                        "minValue": $scope.minValue,
                                        "maxValue": $scope.maxValue,
                                        "rewardType": ($scope.ruleType == "points" ? 1 : 2)
                                    };

                                    // Fields are valid and the HTTP request can be sent.
                                    areFieldsValid = true;
                                }
                            }
                        }
                    }
                    else {
                        $scope.error = "Please fill all fields.";
                    }
                }

                // Then executes the request.
                if (areFieldsValid) {
                    $http({
                        method: method,
                        url: postUrl,
                        data: ruleData
                    }).then(
                        function success(response) {
                            if (response.status == 200) {
                                // The user successfully added an event and Spongebob
                                // is overexcited. This function is located in the
                                // "rules.jade" view.
                                overexcitedBob();
                                // Shows the success panel.
                                $scope.successPosting = true;
                            }
                            else {
                                $scope.error = "An error occured, please retry.";
                                $scope.btnExecuteDisabled = false;
                            }
                        },
                        function error(response) {
                            $scope.error = "An error occured, please retry.";
                            $scope.btnExecuteDisabled = false;
                        }
                    );
                }
                else {
                    $scope.btnExecuteDisabled = false;
                }
            };
        });
})();
