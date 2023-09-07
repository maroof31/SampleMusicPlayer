# SampleMusicPlayer
# Music Player App with Jetpack Compose

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

Welcome to the Music Player App, a feature-rich Android application built with Jetpack Compose. This app follows the MVVM (Model-View-ViewModel) architecture pattern and incorporates several key technologies, including Retrofit for network communication, Glide for image loading, and Hilt for dependency injection. The app consists of two main screens: the "For You" screen, which displays a list of songs, and the "Player" screen, which shows the currently playing song along with playback controls. Notably, the background of the "Player" screen dynamically changes based on the cover image of the current song.


 <img src="https://github.com/maroof31/SampleMusicPlayer/blob/master/screenshots/Screenshot_2023-09-07-11-55-42-54_6be19058ca385e9ac801d80dd9b806d5.jpg" alt="Splash Screen" width="200">




## Features

**1. Song List Screen**
   - Browse a list of songs.
   - Effortlessly scroll through your music collection.
   - Quickly find and select songs for playback.

**2. Player Screen**
   - Play, pause, and control playback of songs.
   - Display album art, song title, and artist information.
   - Dynamic background based on the cover image of the current song.
   - Navigate between songs in the playlist.
   - Adjust volume and seek to specific playback positions.

**3. Smooth User Experience**
   - Elegant animations and transitions for a polished feel.
   - Responsive and intuitive user interface for seamless interaction.
   - Easily switch between the song list and player screens.

**4. MVVM Architecture**
   - Clean separation of data, presentation, and user interface.
   - Maintainable and extensible codebase.
   - Improved testability for code quality assurance.

**5. Dependency Injection with Hilt**
   - Streamlined dependency management.
   - Simplified codebase with reduced boilerplate.
   - Robust support for testing and debugging.

**6. Network Requests with Retrofit**
   - Efficient and reliable communication with a backend server.
   - Support for RESTful API integration.
   - Easy configuration and customization of network requests.

**7. Efficient Image Loading with Glide**
   - Fast and smooth loading of album artwork.
   - Caching to minimize network usage and enhance user experience.

## Screenshots

![Song List](screenshots/song_list.png)
![Player Screen](screenshots/player_screen.png)

## Video Playback

[Watch a demo of the Music Player App in action](screenshots/Record_2023-09-07-11-53-32.mp4)

###  MVVM Architecture
- **Separation of Concerns**: MVVM separates the app into three distinct layers—Model, View, and ViewModel—resulting in cleaner, more organized code.
- **Testability**: ViewModel allows for easier unit testing of the app's business logic, enhancing code quality and reliability.
- **Data Binding**: MVVM naturally integrates with data binding, simplifying the synchronization of UI elements with data changes.
- **Scalability**: MVVM is well-suited for large, complex apps as it encourages a modular and maintainable architecture.


###  ExoPlayer
- **Versatility**: ExoPlayer is highly customizable, making it suitable for a wide range of media playback scenarios, from simple audio playback to complex video streaming.
- **Adaptive Streaming**: ExoPlayer supports adaptive streaming formats like DASH and HLS, providing seamless playback quality adjustments based on network conditions.
- **Wide Format Support**: It can handle various media formats and codecs, ensuring compatibility with different types of content.
- **Low Latency**: ExoPlayer is optimized for low latency, making it suitable for real-time applications like music streaming.

###  Hilt Dependency Injection
- **Simplifies Dependency Management**: Hilt eliminates the need for manual dependency injection setup, reducing boilerplate code and making the codebase cleaner and more maintainable.
- **Scalability**: It simplifies the addition of new components or dependencies as the app evolves, ensuring a scalable and extensible architecture.
- **Improved Testing**: Hilt's built-in support for testing makes it easier to write unit tests and ensure code correctness.

### Jetpack Compose
- **Declarative UI**: Jetpack Compose enables you to describe the UI in a more concise and intuitive manner, reducing UI-related bugs and improving code readability.
- **Reusable Components**: Compose encourages the creation of reusable UI components, promoting a modular and maintainable codebase.
- **Consistency**: The Material Library offers pre-designed UI elements and guidelines, ensuring a consistent and visually pleasing user interface.

## Installation

To run the Music Player app on your local machine, follow these steps:

1. Clone this repository:

   ```shell
   git clone https://github.com/maroof31/SampleMusicPlayer.git
Open the project in Android Studio.

Build and run the app on an emulator or physical device.

Configuration
The Music Player app relies on the following dependencies:

Jetpack Compose: Modern Android UI toolkit.
Retrofit: HTTP client for making network requests.
Glide: Image loading library for displaying album art.
Hilt: Dependency injection framework for Android.
ExoPlayer: Media player library for audio and video playback.
You can add these dependencies to your build.gradle file as needed.

Usage
The app is designed for playing and managing your music collection. Here's how you can use it:

Launch the app.
Navigate to the "For You" screen to see a list of available songs.
Select a song to play it.
Once a song is playing, go to the "Player" screen to see the song details and playback controls.
Enjoy your music with the dynamic background that changes based on the cover image of the current song.
Contributing
Contributions are welcome! If you'd like to improve the app or fix any issues, please follow these steps:

Fork the repository.
Create a new branch for your changes.
Make your changes and commit them.
Push your changes to your fork.
Submit a pull request to the main repository.
License
This Music
