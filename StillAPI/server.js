
//Code Attribution
//This code was adapted from Firebase Docs
//https://firebase.google.com/docs/firestore/query-data/get-data#node.js_5
 
const express = require('express');
const db = require('./firebase');
const app = express();
 
app.get('/songs', async (req, res) => {
    try {
        const songsSnapshot = await db.collection('songs').get();
        const songsList = songsSnapshot.docs.map(doc => doc.data());
        res.json(songsList);
    } catch (error) {
        res.status(500).send('Error fetching songs');
    }
});
//Code Attribution
//https://stackoverflow.com/questions/39750550/node-express-rest-api-application-passing-parameters-to-get-method
//Tom
//https://stackoverflow.com/users/2865923/tom
app.get('/song/:id', async (req, res) => {
    var id = req.params.id;
  try {
      const songsSnapshot = await db.collection('songs').where('SongName', '==', id).get();
      const songsList = songsSnapshot.docs.map(doc => doc.data());
      res.json(songsList);
  } catch (error) {
      res.status(500).send('Error fetching songs');
  }
});
app.get('/WindDownSongs', async (req, res) => {
  try {
      const songsSnapshot = await db.collection('songs').where('Genre', '==', 'Wind Down').get();
      const songsList = songsSnapshot.docs.map(doc => doc.data());
      res.json(songsList);
  } catch (error) {
      res.status(500).send('Error fetching songs');
  }
});
app.get('/BreatheSongs', async (req, res) => {
  try {
      const songsSnapshot = await db.collection('songs').where('Genre', '==', 'Breathe').get();
      const songsList = songsSnapshot.docs.map(doc => doc.data());
      res.json(songsList);
  } catch (error) {
      res.status(500).send('Error fetching songs');
  }
});
app.get('/FocusSongs', async (req, res) => {
  try {
      const songsSnapshot = await db.collection('songs').where('Genre', '==', 'Focus').get();
      const songsList = songsSnapshot.docs.map(doc => doc.data());
      res.json(songsList);
  } catch (error) {
      res.status(500).send('Error fetching songs');
  }
});
app.get('/MeditateSongs', async (req, res) => {
  try {
      const songsSnapshot = await db.collection('songs').where('Genre', '==', 'Meditate').get();
      const songsList = songsSnapshot.docs.map(doc => doc.data());
      res.json(songsList);
  } catch (error) {
      res.status(500).send('Error fetching songs');
  }
});
//app.get(/song)
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});

 