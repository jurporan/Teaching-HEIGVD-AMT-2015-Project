var express = require('express');
var router = express.Router();
var request = require('request');
var deferred = require('deferred');
var multer  = require('multer')
var upload = multer({ dest: "public/img/badges" })

var apiKey = '82f72322-a7ab-40d6-8190-c89c261ff0c2';
var restApiServerAddress = 'http://localhost:8080';
var commentBadgeId = 0;
var kickBadgeId = 0;
var voteBadgeId = 0;
var levelBadgeId = 0;

function getUsers() {
    // Init data promise.
    var defer = deferred();

    // Makes a POST request on the application to get users.
    request(restApiServerAddress + '/Gary/api/applications/' + apiKey + '/users', function(error, response, body) {
        if (!error && response.statusCode == 200) {
            // Get users as JSON values.
            var users = JSON.parse(body);
            // If there is still no user, create them.
            if (!users[0]) {
                console.log("No existing user, create new ones.");

                // User's data.
                var data = [
                    {
                        "id": 123
                    },
                    {
                        "id": 321
                    },
                    {
                        "id": 132
                    }
                ];

                // Convert user objects into HTTP requests and send them.
                var n = data.length;
                for (var i = 0; i < n; ++i) {
                    request.post({
                        url: restApiServerAddress + '/Gary/api/applications/' + apiKey + '/users',
                        json: data[i]
                    });
                }

                defer.resolve({
                    error: 0,
                    data: data
                });
            }
            else {
                defer.resolve({
                    error: 0,
                    data: users
                });
            }
        }
        else {
            defer.resolve({
                error: 1,
                data: null
            });
        }
    });

    // Return the promise object.
    return defer.promise;
}

function getBadges() {
    // Init data promise.
    var defer = deferred();

    // Makes a POST request on the application to get badges.
    request(restApiServerAddress + '/Gary/api/applications/' + apiKey + '/badges', function(error, response, body) {
        if (!error && response.statusCode == 200) {
            // Get users as JSON values.
            var badges = JSON.parse(body);

            // If there is still no badge, create them.
            if (!badges[0]) {
                console.log("No existing badge, create new ones.");

                // User's data.
                var data = [
                    {
                        "name": "Welcome",
                        "description": "Congratulation you just signed-in to Gary!",
                        "imageUrl": "welcome.png"
                    },
                    {
                        "name": "First comment",
                        "description": "You successfully poster your first comment!",
                        "imageUrl": "firstcomment.png"
                    },
                    {
                        "name": "Level 3!",
                        "description": "You reached level 3, AAAW YEAAAAH!",
                        "imageUrl": "level3.png"
                    },
                    {
                        "name": "Upvote/Downvote",
                        "description": "Yay, you clicked on the green/red thumb!",
                        "imageUrl": "happy.png"
                    },
                    {
                        "name": "Kick",
                        "description": "Haha you just kicked that nasty troll!",
                        "imageUrl": "kick.png"
                    }
                ];

                // Convert user objects into HTTP requests and send them.
                var n = data.length;
                // Used to know if the posted request is the last one, to release
                // the promise.
                for (var i = 0; i < n; ++i) {
                    (function(index) {
                        request.post({
                            url: restApiServerAddress + '/Gary/api/applications/' + apiKey + '/badges',
                            json: data[index]
                        }, function(error, response, body) {
                            // Get badges ID's
                            if (data[index].name === "First comment") {
                                commentBadgeId = parseInt(body);
                                console.log("Add badge #" + index + ": First comment? " + data[index].name + " - ID: " + parseInt(body));
                            }
                            else if (data[index].name === "Upvote/Downvote") {
                                voteBadgeId = parseInt(body);
                                console.log("Add badge #" + index + ": Upvote/Downvote? " + data[index].name + " - ID: " + parseInt(body));
                            }
                            else if (data[index].name === "Kick") {
                                kickBadgeId = parseInt(body);
                                console.log("Add badge #" + index + ": Kick? " + data[index].name + " - ID: " + parseInt(body));
                            }
                            else if (data[index].name === "Level 3!") {
                                levelBadgeId = parseInt(body);
                                console.log("Add badge #" + index + ": Level 3? " + data[index].name + " - ID: " + parseInt(body));
                            }

                            data[index].id = parseInt(body);

                            if (index === (n - 1)) {
                                console.log("Badges created, I can now release the promise.");
                                defer.resolve({
                                    error: 0,
                                    data: data
                                });
                            }
                        });
                    })(i);
                }
            }
            else {
                defer.resolve({
                    error: 0,
                    data: badges
                });
            }
        }
        else {
            defer.resolve({
                error: 1,
                data: null
            });
        }
    });

    // Return the promise object.
    return defer.promise;
}

function getRules() {
    // Init data promise.
    var defer = deferred();

    // Makes a POST request on the application to get rules.
    request(restApiServerAddress + '/Gary/api/applications/' + apiKey + '/rules', function(error, response, body) {
        if (!error && response.statusCode == 200) {
            // Get rules as JSON values.
            var rules = JSON.parse(body);

            // If there is still no rule, create the default ones.
            if (!rules[0]) {
                console.log("No existing rule, create default ones.");

                // Rules' data.
                var data = [
                    {
                        "typeOfEvent": "Post a comment",
                        "ruleParameter": 100,
                        "penalty": false,
                        "minValue": null,
                        "maxValue": null,
                        "rewardType": 1
                    },
                    {
                        "typeOfEvent": "Post a comment",
                        "ruleParameter": commentBadgeId,
                        "penalty": false,
                        "minValue": null,
                        "maxValue": null,
                        "rewardType": 2
                    },
                    {
                        "typeOfEvent": "Kick a troll",
                        "ruleParameter": 200,
                        "penalty": false,
                        "minValue": null,
                        "maxValue": null,
                        "rewardType": 1
                    },
                    {
                        "typeOfEvent": "Kick a troll",
                        "ruleParameter": kickBadgeId,
                        "penalty": false,
                        "minValue": null,
                        "maxValue": null,
                        "rewardType": 2
                    },
                    {
                        "typeOfEvent": "Upvote",
                        "ruleParameter": 100,
                        "penalty": false,
                        "minValue": null,
                        "maxValue": null,
                        "rewardType": 1
                    },
                    {
                        "typeOfEvent": "Upvote",
                        "ruleParameter": voteBadgeId,
                        "penalty": false,
                        "minValue": null,
                        "maxValue": null,
                        "rewardType": 2
                    },
                    {
                        "typeOfEvent": "Downvote",
                        "ruleParameter": 50,
                        "penalty": false,
                        "minValue": null,
                        "maxValue": null,
                        "rewardType": 1
                    },
                    {
                        "typeOfEvent": "Downvote",
                        "ruleParameter": voteBadgeId,
                        "penalty": false,
                        "minValue": null,
                        "maxValue": null,
                        "rewardType": 2
                    },
                    {
                        "typeOfEvent": "Level 3",
                        "ruleParameter": levelBadgeId,
                        "penalty": false,
                        "minValue": null,
                        "maxValue": null,
                        "rewardType": 2
                    }
                ];

                console.log("Create rules with following badges IDs: " + commentBadgeId + " - " + kickBadgeId + " - " + voteBadgeId + " - " + levelBadgeId + ".");
                // Convert user objects into HTTP requests and send them.
                var n = data.length;
                for (var i = 0; i < n; ++i) {
                    (function(index) {
                        request.post({
                            url: restApiServerAddress + '/Gary/api/applications/' + apiKey + '/rules',
                            json: data[i]
                        }, function(error, response, body) {
                            console.log(body);

                            if (index === (n - 1)) {
                                console.log("Rules created, I can now release the promise.");
                                defer.resolve({
                                    error: 0,
                                    data: data
                                });
                            }
                        });
                    })(i);
                }
            }
            else {
                defer.resolve({
                    error: 0,
                    data: rules
                });
            }
        }
        else {
            defer.resolve({
                error: 1,
                data: null
            });
        }
    });

    // Return the promise object.
    return defer.promise;
}

/* GET home page. */
router.get('/', function(req, res) {
    var usersPromise = getUsers();

    usersPromise(function(users) {
        if (users.error === 1) {
            res.render('index', {
                title: 'Ultimate Gary Events Test-Console Two-Thousand-Fifteen!',
                error: 'Sorry I cannot get application\'s users, please retry in a while :('
            });
        }
        else {
            console.log("Users successfully created/got!");
            var badgesPromise = getBadges();

            badgesPromise(function(badges) {
                if (badges.error === 1) {
                    res.render('index', {
                        title: 'Ultimate Gary Events Test-Console Two-Thousand-Fifteen!',
                        error: 'Sorry I cannot get application\'s badges, please retry in a while :('
                    });
                }
                else {
                    console.log("Badges successfully created/got.");

                    var rulesPromise = getRules();

                    rulesPromise(function(rules) {
                        if (rules.error === 1) {
                            res.render('rules', {
                                title: 'Ultimate Gary Events Test-Console Two-Thousand-Fifteen!',
                                error: 'Sorry I cannot get application\'s rules, please retry in a while :('
                            });
                        }
                        else {
                            console.log("Rules successfully created/got.");

                            // Send data to index view.
                            res.render('index', {
                                title: 'Ultimate Gary Events Test-Console Two-Thousand-Fifteen!',
                                users: users.data,
                                badges: badges.data,
                                rules: rules.data,
                                apiKey: apiKey,
                                restApiServerAddress: restApiServerAddress
                            });
                        }
                    });
                }
            });
        }
    });
});

/* GET rules page. */
router.get('/rules', function(req, res) {
    var badgesPromise = getBadges();

    badgesPromise(function(badges) {
        if (badges.error === 1) {
            res.render('rules', {
                title: 'AW YEAH, you can manage you own rules!',
                error: 'Sorry I cannot get application\'s badges, please retry in a while :('
            });
        }
        else {
            console.log("Badges successfully created/got.");

            var rulesPromise = getRules();

            rulesPromise(function(rules) {
                if (rules.error === 1) {
                    res.render('rules', {
                        title: 'AW YEAH, you can manage you own rules!',
                        error: 'Sorry I cannot get application\'s rules, please retry in a while :('
                    });
                }
                else {
                    console.log("Rules successfully created/got.");

                    // Send data to rules view.
                    res.render('rules', {
                        title: 'AW YEAH, you can manage you own rules!',
                        badges: badges.data,
                        rules: rules.data,
                        apiKey: apiKey,
                        restApiServerAddress: restApiServerAddress
                    });
                }
            });
        }
    });
});

// Badge's logo upload route.
router.post('/upload', upload.single('file'), function(req, res, next) {
    console.log("File '" + req.file.originalname + "' successfully uploaded.");
    res.end(req.file.filename);
});

module.exports = router;
