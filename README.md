![Beige Feminine Personal LinkedIn Banner](https://github.com/user-attachments/assets/da1eb80b-a653-4e4c-81a8-d38a9f1abd89)

# Still: A Meditation and Mindfulness App

**By InvestIQ**

## 🔵 Table of Contents
1. [Overview](#overview)
2. [Team Members](#team-members)
3. [Purpose of the App](#purpose-of-the-app)
4. [Design Considerations](#design-considerations)
5. [GitHub and GitHub Actions](#github-and-github-actions)
6. [Release Notes](#release-notes)
7. [Main Features](#main-features)
   - [Focus](#focus)
   - [Meditate](#meditate)
   - [Wind Down](#wind-down)
   - [Breathe](#breathe)
8. [Additional Features](#additional-features)
   - [Notepad](#notepad)
   - [Charging Animations - Lightbulb](#charging-animations---lightbulb)
   - [Mood Tracker](#mood-tracker)
   - [Arcade Space](#arcade-space)
   - [Chat with Serena](#chat-with-serena)
9. [Requirements](#requirements)
   - [Registration and Login](#registration-and-login)
   - [Biometric Authentication](#biometric-authentication)
   - [Settings Customization](#settings-customization)
   - [Rest API Integration](#rest-api-integration)
   - [Offline mode with sync](#offline-mode-with-sync)
   - [Real time notifications](#real-time-notifications)
   - [Multi-Language Support](#multi-language-support)
10. [SSO](#sso)
11. [In Depth Analyses of Serena-AI](#in-depth-analyses-of-serena-ai)
12. [Serenity API](#serenity-api)
13. [Installation](#installation)
14. [Screenshots](#screenshots)
15. [Demo Videos](#demo-videos)
16. [License](#license)
17. [Contact](#contact)
18. [References](#references)

## Overview

**Still** is a meditation and mindfulness app designed to help you find an idyllic space within yourself. The app offers a diverse range of guided meditations, soothing soundscapes, and mindfulness exercises tailored for both beginners and experienced practitioners. With a comforting, user-friendly interface, Still makes it easy to find your calm and peace.

*(Bell & Troy, 2024; Tartakovsky & Pedersen, 2022)*

---

## Team Members

- Leighché Jaikarran (Team Lead)
- Naiya Haribhai (NFR Developer)
- Tanya Christine Govender (Full-Stack developer)
- Vikhayle (Full-Stack developer)

---

## Purpose of the App 
Whether you're seeking to reduce stress, improve focus, or cultivate a deeper sense of inner peace, Still is your perfect companion on the journey to a calmer, more centered you. In relation to other similar apps on the market, Still is a competitive app as unlike other mindfulness application currently available on the market (such as Calm, HeadSpace and Forest), all features of the application are completely free, which allows for more people (who may not have the oppurtunity to use subscription based mindfulness applications) to access mindfulness content 
*(Jackson, 2022; Labib, 2021; Tartakovsky et al., 2022)*.

Still aims to bring peace, focus and inner peace to a broader consumer base, and to enable the public to have better mental health. 

## Design Considerations
In terms of the UI, we have chosen to go with a dark blue and yellow colour scheme with an occassional pop of colour.

We chose the dark blue colour as it creates a sense of calmness and peace for the user, which is sort of atmosphere that this application aims to provide for it's users. Yellow was used as it often associated with positive emotions, which is the sort of emotions that Still aims to invoke in it's users.  This can be seen in the screensot below
![image](https://github.com/user-attachments/assets/937a89d6-ea97-4c6e-9c99-cfd7fbf4c94a)


The occassional pop of colour has been added to ensure that the app is not extremely monochromatic to ensure that the user does not get bored of seeing the same 2 colours throughout the app- this can be seen in the image below:

![image](https://github.com/user-attachments/assets/f385ebb6-1e10-4b7a-801c-f82415f43d49)


## GitHub and GitHub Actions
GitHub is used to store and share code with others, while GitHub Actions provides a continuous integration and continuous delivery (CI/CD) platform for developers to test their code and automate those tests 
*(GitHub, 2024)*.

Our team used GitHub to safely store our code, so team members have access to the most recent code and in the event of anything going wrong during development, it was guaranteed that we would have a working version to roll back to; furthermore, it allowed for collaboration as various developers could commit to different branches, while working on different features, ensuring that they do not get in each other's way during development
*(GitHub, 2024)*.

Our team used CircleCI and SonarQube along with GitHub Actions to automate checking our code for any vulnerablities and to test the code to ensure that it works not only on the developer's machines but on users' devices as well 
*(GitHub, 2024)*.

## Release Notes
### Updates to the Application: 
1. Notepad Functionality: Still now offers a notepad for users to jot down their thoughts and feelings, serving as a journal so that users can track their mindfulness and inner peace journey - this feature is available online and offline. This feature has been added to allow users to track their growth and see the difference that Still makes in their lives.
   
2. Power Up the Lightbulb: This feature is an animation that powers up a lightbulb based on the number of songs that the user has listened to, for example, after every 5 songs that the user has listened to, the lightbulb will glow brighter and brighter, this feature has been added as it motivates users to listen to more songs, and do more of what they aim to do by listening to those songs, for example, if the user wants to focus, and they are listening to the songs within the Focus category, they may be motivated to focus even more because of the sense of achievement that is felt when seeing the lightbulb glow.
   
3. Multi-language support: Still now supports 2 languages in addition to English, those being Afrikaans and Zulu, as this is an application made by South Africans, for South Africans, the reason why we have added this feature is to break down the langauge divide and provide as many people as possible with access to an application that help them find inner peace and be mindful.
   
4. Real-time notifications: Still now provides a user with a notification in their notification center once a note has been saved to the online database, in addition, it also provides a suggestion on what a user should do depending on their input into the mood tracker, for example, if the user says that they are feeling sad then a notification will pop up in their notification center suggesting that the user should chat to Serena AI - this has been added as it provides more feedback to the user and directs the user to getting help (in the case of the mood tracker notification).
   
5. Offline mode: A user can save a note while they are not connected to any Wi-Fi network, any mobile hotspot or any mobile data, and the note will sync with the online database as soon as the user is back online - this has been added as users can use the app for an extended amount of time as they don't need to use Wi-Fi, a mobile hotspot or mobile data in order to use this feature of the application.
   
6. Biometric Authentication: If a user's mobile device supports biometric authentication, the user can log in with biometric authentication, this not only skips the login process, whereby the user no longer enters their username and password (which improves security) but also ensures that only the user whom the account belongs to, will be able to access the application, furthermore, the login process is made a lot easier, so the user can log into the app quicker.
*(Jackson, 2022; Labib, 2021; Tartakovsky et al., 2022)*


## Main Features

### Focus
Immerse yourself in calming soundscapes to enhance your meditation experience.
*(Jackson, 2022; Labib, 2021; Tartakovsky et al., 2022)*

### Meditate
Access guided meditation sessions designed to suit various needs, from stress relief to deep relaxation.
*(Calm, 2024; Google Play Store, 2024)*

### Wind Down
Unwind at the end of the day with exercises and sounds specifically curated to help you relax and prepare for restful sleep.
*(Calm, 2024; Google Play Store, 2024)*

### Breathe
Follow guided breathing exercises that center your mind and body, promoting overall well-being.
*(Jackson, 2022; Labib, 2021; Tartakovsky et al., 2022)*

![image](https://github.com/user-attachments/assets/59f861dc-995d-40d2-9698-a2bb96e0b7c2)
---

## Additional Features

### Notepad
- Keep track of your thoughts and daily activities.
- Your notes are securely locked behind biometric authentication, ensuring privacy and peace of mind.
*(Bell et al., 2024; Calm, 2024)*

### Charging Animations - Lightbulb
- Watch a lightbulb "charge" as you progress on your mindfulness journey, symbolizing the renewal of your thinking.
*(Google Play Store, 2024; Jackson, 2022; Labib, 2021)*

### Mood Tracker
- Monitor your emotional state over time, helping you gain insights into your mental well-being.
*(Bell et al., 2024; Calm, 2024)*

### Arcade Space
Take a break and relax with mini-games designed to entertain and de-stress:

- **Memory Game**: Sharpen your mind with engaging memory challenges.
- **Trivia**: Test your knowledge and learn something new while having fun.

*(Google Play Store, 2024; Jackson, 2022; Labib, 2021)*

### Chat with Serena
- Engage with **Serina**, our empathetic AI companion, who will guide you through your emotions and provide support on your emotional journey.
*(Tartakovsky et al., 2022; Tartakovsky, 2022)*

---

## Requirements

### Registration and Login
- Users must be able to register and log in using Single Sign-On (SSO).
- If users don't have an account, they will be prompted to create one.
*(Tartakovsky et al., 2022; Tartakovsky, 2022)*

### Biometric Authentication
- Implement fingerprint or facial recognition for secure user authentication.
*(Calm, 2024; Google Play Store, 2024; Jackson, 2022)*

### Settings Customization
- Users must be able to change their settings in the app.
*(Labib, 2021)*

### REST API Integration
- Connect the app to a custom REST API that communicates with a database.

- Link to the API: https://still-2004.uc.r.appspot.com

- The API has 4 Main enpoints:

  1. https://still-2004.uc.r.appspot.com/WindDownSongs
 
  2. https://still-2004.uc.r.appspot.com/BreatheSongs
 
  3. https://still-2004.uc.r.appspot.com/MeditateSongs
 
  4. https://still-2004.uc.r.appspot.com/FocusSongs
    
*(Calm, 2024; Google Play Store, 2024; Jackson, 2022; Labib, 2021; Tartakovsky et al., 2022)*

### Offline Mode with Sync
- Allow users to saved notes while offline with the notes syncing when reconnected.
*(Bell et al., 2024; Calm, 2024; Google Play Store, 2024; Jackson, 2022; Labib, 2021; Tartakovsky et al., 2022)*

### Real-time Notifications
- The user is recommended an action in their notification center after they have filled out the mood tracker.
*(Tartakovsky et al., 2022; Tartakovsky, 2022)*

### Multi-language Support
- Support the following languages
   1. English
   2. Afrikaans
   3. Zulu
*(Bell et al., 2024; Calm, 2024)*

<img src="https://github.com/user-attachments/assets/03e57abe-fbce-407c-bb0d-857729814442" width="300px" />

---

## SSO
- Users must be able to register and log in using Single Sign-On (SSO).
- The STILL Application makes use of Google Authentication and single sign on:
    It can be seen below that an account was added to firebase using google single sign on
![Screenshot 2024-09-30 at 23 05 55](https://github.com/user-attachments/assets/6f7defd8-2a2d-48d1-bc32-35cada359a64)
*(Tartakovsky et al., 2022; Tartakovsky, 2022)*

--- 
## In Depth Analyses of Serena-AI

Integrating Gemini AI as an Emotional Assistant

- In our Still app, we integrated Gemini AI to function as a personalized emotional assistant. This AI-powered companion, named Serina, helps users manage their emotions and provides personalized guidance on their mindfulness journey. Here's how Gemini AI enhances the app's capabilities:

- Emotion Detection: Using advanced natural language processing (NLP) and sentiment analysis, Gemini AI interacts with users and interprets their emotional states based on the responses provided during conversations.

- Personalized Assistance: Once the emotional state is detected, Gemini AI customizes its responses to offer support. For instance, if a user feels anxious, Serina suggests calming exercises, soundscapes, or guided meditations tailored to relieve stress.

- Continuous Learning: Gemini AI continuously improves its interaction with the user by learning from previous emotional inputs. This makes it better suited to offer personalized recommendations over time.

- Real-Time Interaction: Users can engage with Serina in real time, receiving instant feedback and emotional support. This makes the interaction feel empathetic and human-like, enhancing the user's emotional well-being.

- Mindfulness Guidance: Beyond basic emotional support, Gemini AI serves as a guide through the mindfulness exercises within the app. Based on user moods and preferences, Serina suggests suitable meditation sessions, breathing exercises, or soothing soundscapes.

This integration makes Still unique, providing users with a constant emotional companion that adapts to their mental state, offering meaningful suggestions for maintaining balance and calm.

---

## Serenity API

In the Still app, we use an API to fetch calming songs and soundscapes that enhance the user's meditation and mindfulness experience. Here’s how it works:

**How the Song API Integration Works**

- API Request: The app sends a request to an external music API that provides access to a collection of songs designed for relaxation, mindfulness, and meditation. This request is made through HTTP protocols using GET requests, asking for specific types of music based on user preferences or emotional states detected by Gemini AI.
  
- Data Filtering: The API allows us to filter songs based on different criteria, such as:

- Mood-based categories: Relaxing, stress-relief, focus-enhancing, etc.
- Song genre: Nature sounds, instrumental, ambient, etc.
- Duration: Short tracks for quick meditations or longer ones for deep relaxation. These filters allow us to serve relevant songs that match the user’s current mood or meditation session.
- Fetching Songs: Once the request is made, the API responds with song data, including:
- Track URL: The direct link to stream or download the song.
- Metadata: Information like song title, artist, genre, and duration.
- Audio Formats: Depending on the API, it may provide the song in different formats (e.g., MP3, WAV).


## Installation
### From GitHub
1. Clone the repository:
   ```bash
   git clone https://github.com/InvestIQ/still.git
   ```
  
2. Open the application within Android Studio
  
3. Connect your mobile device to your computer - wirelessly or wired by following the prompts on screen (optional if you want to use the application on your mobile device)

4. Start the application by clicking the green triangle

5. Enjoy the Stillness!

### From the Play Store 
1. Go to the Play Store on your android device and log in with your Google account.

2. Search for 'Still' in the search bar at the top

3. Install the app by clicking 'Install'

4. Enjoy the Stillness!
   
## Screenshots 
### These screenshots will be used in the screenshots section of the application description on the Play Store.
<table>
  <tr>
    <td>
      <img src="https://github.com/user-attachments/assets/e9fbbf61-b54e-4d8f-aaf3-3857bd2b697b" width="200" />
      <p>This is the sign up page, here the user can create an account or sign in with Google SSO. </p>
    </td>
    <td>
      <img src="https://github.com/user-attachments/assets/a92b84d4-f755-4b1d-8b8a-997da2d12744" width="200" />
      <p>This is the home page of the app, allowing the user to choose any of the features that the app has.</p>
    </td>
  </tr>
  <tr>
    <td>
      <img src="https://github.com/user-attachments/assets/2a399a18-2cd1-425e-8481-f5af7cf50a5c" width="200" />
      <p>This page displays all the music in a certain category, the user can click on one of the songs to listen to it.</p>
    </td>
    <td>
      <img src="https://github.com/user-attachments/assets/8d70ff86-a65d-494a-ba2e-c40f28ba85a8" width="200" />
      <p>This is the screen that the user will see when they play music.</p>
    </td>
  </tr>
  <tr>
    <td>
      <img src="https://github.com/user-attachments/assets/f30750aa-2a1e-4e6c-a5a7-a6b8b1e0e4c6" width="200" />
      <p>This screen displays one of the quiz game questions.</p>
    </td>
    <td>
      <img src="https://github.com/user-attachments/assets/17977ef6-f8f6-418d-afae-5d2a54a1e198" width="200" />
      <p>This screen displays the memory game.</p>
    </td>
  </tr>
  <tr>
    <td>
      <img src="https://github.com/user-attachments/assets/8b66dfad-b744-4cf1-9b45-9b800ffe2e7a" width="200" />
      <p>This is the settings page of the app, the user can edit their email address, their password and delete their account.</p>
    </td>
    <td>
      <img src="https://github.com/user-attachments/assets/d76d5be2-f6a8-4eb0-a74f-b18c4e42bc13" width="200" />
      <p>This is the login page of the app, the user can log in with their email address or with Google SSO.</p>
    </td>
  </tr>
  <tr>
    <td>
      <img src="https://github.com/user-attachments/assets/748c0009-411e-4d8a-ab66-23e68a785fff" width="200" />
      <p>This is the mind hub, it allows for the user to interact with Serea-AI, the vibe check and powering up.</p>
    </td>
    <td>
      <img src="https://github.com/user-attachments/assets/309e5f4a-4f37-4e5e-b569-f4c0a84e1cd1" width="200" />
      <p>This screen allows the user to interact with Serena-AI.</p>
    </td>
      </tr>
  <tr>
     <td>
      <img src="https://github.com/user-attachments/assets/8cb5cbef-e200-4d18-ba51-e0aff5c87e27" width="200" />
      <p>This screen displays the buttons in Zulu (multi-language support).</p>
    </td>
     <td>
      <img src="https://github.com/user-attachments/assets/2a6e9dea-2437-4221-afa9-9f0ff2b28c5e" width="200" />
      <p>This is the realtime notification that will pop up after the user saves a note.</p>
    </td>
  </tr>
    <tr>
     <td>
      <img src="https://github.com/user-attachments/assets/14145f5c-d57f-4b92-be2a-9f2d0c01d650" width="200" />
      <p>This screen allows a user to view all their notes.</p>
    </td>
     <td>
      <img src="https://github.com/user-attachments/assets/9a1b0d30-826b-460b-9b31-e50c664608cc" width="200" />
      <p>This screen allows a user to view one of their notes and edit it.</p>
    </td>
  </tr>
      <tr>
     <td>
      <img src="https://github.com/user-attachments/assets/2d2fcc6b-1c48-4df8-a74b-1c4159c42323" width="200" />
      <p>This screen allows a user to save a new note.</p>
    </td>
     <td>
      <img src="https://github.com/user-attachments/assets/c77bc09c-5d2e-4c23-9356-d102bc055aea" width="200" />
      <p>This screen allows the user to change the language of the app in Settings.</p>
    </td>
  </tr>
      
</table>

---

## Demo Videos
Watch our demo video to see Still in action:

<img src ="recording copy.gif"  />

<div align="left">
  <img src="https://media3.giphy.com/media/rrOif8vmuM6g05Zha5/giphy.gif?cid=6c09b9525yudzzl1gio0aotte4jpryu1uy7dlflc01rk9s3i&ep=v1_internal_gif_by_id&rid=giphy.gif&ct=s" alt="Cloud Image" width="300">
</div>

<br/>
Part 2 Demo Video: 
   <br/>
<a href="https://youtu.be/8q-LnL8vNBw" target="_blank">
  <img src="https://img.shields.io/badge/YouTube-FF0000?style=for-the-badge&logo=youtube&logoColor=white" alt="YouTube Icon">
</a>
<p>
Part 3 Demo Video: 
<br/>
<a href="https://youtu.be/WFn2z9TR46M?si=_GCuuS73cv-DKBCo" target="_blank">
  <img src="https://img.shields.io/badge/YouTube-FF0000?style=for-the-badge&logo=youtube&logoColor=white" alt="YouTube Icon">
</a>
<p>
<p>
<br/>

   
--- 
## License

This project is licensed under the MIT License - see the LICENSE.md file for details.

--- 

## Contact

For any inquiries or support, please contact us at support@investiq.com.

<img src="https://github.com/user-attachments/assets/ca178955-f264-4396-8b4f-4397c2fe3dde" width="300px" />


---

## References
Bell, C., Troy, B. 2024. Headspace App Review 2024: Pros & Cons, Cost & Who It’s Right For, 30 April 2024. [Online]. Available at: 
https://www.choosingtherapy.com/headspace-review/#:~:text=Pros%20%26%20Cons%20of%20Headspace&text=The%20app%20presents%20mindfulness%20and,off%20with%20other%20meditation%20apps. [Accessed 5  August 2024]. 

Calm. 2024. Calm for Kids, 2024. [Online]. Available at: https://www.calm.com/app/kids [Accessed 7 August 2024].

Combs, M. 2021. This Timer App Is the Answer to Your Focus Woes, 12 August 2021. [Online]. Available at: https://www.cnet.com/tech/services-and-software/this-timer-app-is-the-answer-to-your-focus-woes/  [Accessed 10 August 2024]. 

Dalai Lama. 1998. The Art of Happiness Synopsis, October 26, 1998. [Online]. Available at:  https://www.goodreads.com/book/show/38210.The_Art_of_Happiness [Accessed 15 August 2024].

Frania, N. 2024. How to Use Check-Ins, 19 April 2024. [Online]. Available at: https://support.calm.com/hc/en-us/articles/9699990936731-How-to-Use-Check-Ins [Accessed 19 August 2024]. 

GitHub. 2024. About GitHub and Git, 2024. [Online]. Available at: https://docs.github.com/en/get-started/start-your-journey/about-github-and-git [Accessed 4 November 2024].

GitHub. 2024. Understanding GitHub Actions, 2024. [Online]. Available at: https://docs.github.com/en/actions/about-github-actions/understanding-github-actions [Accessed 4 November 2024]. 

Google Play Store, 2024. Calm App, 2024. [Online]. Available at: https://play.google.com/store/apps/details?id=com.calm.android&pli=1 [Accessed 7 August 2024].
Grennan, M. 2024. Calm Journals, April 2024. [Online]. Available at: https://support.calm.com/hc/en-us/articles/360002773093-Calm-Journals [Accessed 19 August 2024]. 
Jackson, S. 2022. I tried two of the most popular meditation apps, Headspace and Calm, 22 December 2022. [Online]. Available at: https://www.businessinsider.com/meditation-apps-review-headspace-calm-one-was-way-better-2021-9#overall-calm-was-the-clear-winner-for-me-32 [Accessed 19 August].
Kwai, I. 2023. Jimmy Stewart AI Sleep Story,  5 December 2023. [Online]. Available at:  https://www.nytimes.com/2023/12/05/technology/calm-jimmy-stewart-ai.html [Accessed 7 August 2024].

Google. 2024. Gemini - Google DeepMind. [Online]. Available at: https://deepmind.google/technologies/gemini/ [Accessed 19 August 2024].

Labib, M.K. 2021. Forest app review : Most useful App to get things done, 20 July 2021. [Online]. Available at: https://moinulkarim.com/forest-app-review/  [Accessed 7 August 2024]. 

Medium. 2020. Forest App is Good, But is it Good Enough for You? 3 Alternative Focus Apps, 9 August 2020. [Online]. Available at: https://medium.com/@AppGrooves/forest-app-is-good-but-is-it-good-enough-for-you-3-alternative-focus-apps-1d63ed7eb79a  [Accessed 5 August 2024]. 

Nagar, T. 2023. How to build an app like calm, 13 June 2023. [Online]. Available at:  https://devtechnosys.com/insights/how-to-build-an-app-like-calm/ [Accessed 15 August 2024].

Neumann, K. 2024. Calm App Review, 4 July 2024. [Online]. Available at: https://www.forbes.com/health/mind/calm-app-review/ [Accessed 6 August 2024].

OpenAI. 2024. API Deployment Request, 28 September 2024.[Online]. Available at: https://chatgpt.com/c/66f2f102-6100-8013-a142-3f4b7b66d7b5 [Accessed 28 September 2024].

OpenAI. 2024. JSON Object, 29 September 2024.[Online]. Available at: https://chatgpt.com/share/66fa87e1-c77c-8005-b50d-c2c441854ab0 [Accessed 29 September 2024].

OpenAI. 2024. Multilingual XML Layout Support, 3 November 2024. [Online]. Available at: https://chatgpt.com/share/67287ea8-8780-8002-8126-92eed269d09d [Accessed 3 November 2024].

Pichai, S. 2023. Introducing Gemini: our largest and most capable AI model, 06 December 2023. [Online]. Available at: https://blog.google/technology/ai/google-gemini-ai/ [Accessed 19 August 2024]. 
 
Tartakovsky, M. 2022. 2022 Headspace Review: App Pros and Cons, 3 May 2022. [Online]. Available at: https://psychcentral.com/reviews/headspace-app-reviews [Accessed 19 August 2024].

Tartakovsky, M., Pedersen, T. 2022. Headspace vs. Calm: Which App Is Right for You?, 9 May 2022. [Online]. Available at: https://psychcentral.com/reviews/headspace-vs-calm [Accessed 19 August 2024].
