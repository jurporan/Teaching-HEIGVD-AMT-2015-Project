var express = require('express');
var router = express.Router();
var request = require('request');
var deferred = require('deferred');

var apiKey = '75dc51d2-8291-4e37-aaad-d69c5b3041f8';
var commentBadgeId = 0;
var kickBadgeId = 0;
var voteBadgeId = 0;

function getUsers() {
    // Init data promise.
    var defer = deferred();

    // Makes a POST request on the application to get users.
    request('http://localhost:8080/Gary/api/applications/' + apiKey + '/users', function(error, response, body) {
        if (!error && response.statusCode == 200) {
            // Get users as JSON values.
            users = JSON.parse(body);

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
                        url: 'http://localhost:8080/Gary/api/applications/' + apiKey + '/users',
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
    request('http://localhost:8080/Gary/api/applications/' + apiKey + '/badges', function(error, response, body) {
        if (!error && response.statusCode == 200) {
            // Get users as JSON values.
            badges = JSON.parse(body);

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
                        "description": "Haha you just kicked that damn troll!",
                        "imageUrl": "kick.png"
                    }
                ];

                // Convert user objects into HTTP requests and send them.
                var n = data.length;
                // Used to know if the posted request is the last one, to release
                // the promise.
                var requestNumber = 1;
                for (var i = 0; i < n; ++i) {
                    request.post({
                        url: 'http://localhost:8080/Gary/api/applications/' + apiKey + '/badges',
                        json: data[i]
                    }, function(error, response, body) {
                        // Get badges ID's
                        if (data[requestNumber - 1].name === "First comment") {
                            commentBadgeId = parseInt(body);
                        }
                        else if (data[requestNumber - 1].name === "Upvote/Downvote") {
                            voteBadgeId = parseInt(body);
                        }
                        else if (data[requestNumber - 1].name === "Kick") {
                            kickBadgeId = parseInt(body);
                        }

                        if (requestNumber === n) {
                            console.log("Badges created, I can now release the promise.");
                            defer.resolve({
                                error: 0,
                                data: data
                            });
                        }
                        else {
                            ++requestNumber;
                        }
                    });
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
    console.log("Create rules.");

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
        }
    ];

    // Convert user objects into HTTP requests and send them.
    var n = data.length;
    for (var i = 0; i < n; ++i) {
        request.post({
            url: 'http://localhost:8080/Gary/api/applications/' + apiKey + '/rules',
            json: data[i]
        });
    }

    // Return the promise object.
    return data;
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
            var badgesPromise = getBadges();

            badgesPromise(function(badges) {
                if (badges.error === 1) {
                    res.render('index', {
                        title: 'Ultimate Gary Events Test-Console Two-Thousand-Fifteen!',
                        error: 'Sorry I cannot get application\'s badges, please retry in a while :('
                    });
                }
                else {
                    var rules = getRules();

                    // Send new users to view.
                    res.render('index', {
                        title: 'Ultimate Gary Events Test-Console Two-Thousand-Fifteen!',
                        users: users.data,
                        badges: badges.data,
                        rules: rules,
                        apiKey: apiKey
                    });
                }
            });
        }
    });
});

module.exports = router;
