var copilot = require('api-copilot');

// create an API scenario
var scenario = new copilot.Scenario({
    name: 'Scenario',
    summary: 'Test copilot',
    baseUrl: 'http://localhost:3000/api',
});



// créer un utilisateur
scenario.step('create user', function() {
    return this.post({
        url:'/users',
        body: {
            name:
            email:
            password:    
    
});


// ETC ETC....

// l'utilisateur réponds

// à la fin, exporter le scenario
module.exports = scenario;
