var express = require('express');
var router = express.Router();
var request = require('request');
var apiKey = '8e41354b-563d-463a-b1db-9630cef69ce1';

/* GET home page. */
router.get('/', function(req, res) {
    // Makes a POST request on the application to get users.
    request('http://localhost:8080/Gary/api/applications/' + apiKey + '/users', function(error, response, body) {
        if (!error && response.statusCode == 200) {
            // Get users as JSON values.
            users = JSON.parse(body);

            // If there is still no user, create them.
            if (!users[0]) {
                console.log("No user existing, create new ones.");

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

                // Send new users to view.
                res.render('index', {
                    title: 'Ultimate Gary Events Test-Console Two-Thousand-Fifteen!',
                    users: data,
                    apiKey: apiKey
                });
            }
            else {
                // Send got users to view.
                res.render('index', {
                    title: 'Ultimate Gary Events Test-Console Two-Thousand-Fifteen!',
                    users: users,
                    apiKey: apiKey
                });
            }
        }
        else {
            res.render('index', { title: 'Ultimate Gary Events Test-Console Two-Thousand-Fifteen!', error: 'Cannot get application\'s users, please retry in a while.' });
        }
    });
});

module.exports = router;
