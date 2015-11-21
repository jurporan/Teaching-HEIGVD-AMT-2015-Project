var express = require('express');
var router = express.Router();

/* GET home page. */

router.get('/', function(req, res) {
  res.render('index', { title: 'Ultimate Gary Events Test-Console Two-Thousand-Fifteen!' });
});

module.exports = router;
