 
//Code Attribution
//This code was adapted from Firebase Docs
//https://firebase.google.com/docs/firestore/query-data/get-data#node.js_5
const admin = require('firebase-admin');
const serviceAccount = require('');
 
admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://still-2004.firebaseio.com"
});
 
const db = admin.firestore();
module.exports = db;