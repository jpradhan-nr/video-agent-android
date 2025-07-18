[![Community Project header](https://github.com/newrelic/opensource-website/raw/master/src/images/categories/Community_Project.png)](https://opensource.newrelic.com/oss-category/#community-project)

# New Relic Video Agent for Android


The New Relic Video Agent for Android contains multiple modules necessary to instrument video players and send data to New Relic.

## Modules

There are three modules available:

### NewRelicVideoCore

Contains all the base classes necessary to create trackers and send data to New Relic. It depends on the New Relic Agent.

### NRExoPlayerTracker

The video tracker for ExoPlayer2 player. It depends on NewRelicVideoCore.

### NRIMATracker

The video tracker for Google IMA Ads library. It depends on NewRelicVideoCore.

## Installation

### Prerequisites

Install the [New Relic Android Agent](https://docs.newrelic.com/docs/mobile-monitoring/new-relic-mobile-android/install-configure/install-android-apps-gradle-android-studio), and any other needed dependency, like ExoPlayer or Google IMA.

### Install automatically using JitPack

Add the following line inside your root build.gradle:

```
allprojects {
    repositories {
        ...
        
        // Add this line at the end of your repositories
        maven { url 'https://jitpack.io' }
    }
}
```

And inside your app's build.gradle, add the following dependencies:

```
dependencies {
    ...

    // Add this to install the NewRelicVideoCore (required)
    implementation 'com.github.newrelic.video-agent-android:NewRelicVideoCore:v3.0.2'
    
    // Add this to install the ExoPlayer tracker
    implementation 'com.github.newrelic.video-agent-android:NRExoPlayerTracker:v3.0.2'
    
    // Add this to install the Google IMA library tracker
    implementation 'com.github.newrelic.video-agent-android:NRIMATracker:v3.0.2'
}
```

To install an specific version, replace the `master-SNAPSHOT` by a version tag.

### Install manually using AAR files

1. Clone this repo.
2. Open it with Android Studio.
3. Click on **View > Tool Windows > Gradle** to open the gradle tool window.
4. In there, unfold **NRVideoProject > Tasks > build** and double-click on **assemble**. This will generate the AAR libraries inside the module's folder **build > outputs > aar**.
5. In your project, click on **File > New > New Module**,  **Import .JAR/.AAR Package** and then **Next**.
6. Enter the location of the generated AAR file then click **Finish**.
7. Repeat steps 5 and 6 with all the AAR files you want to include.
8. In you app module's build.gradle file, add the following:

```
dependencies {
	...
	implementation project(":NewRelicVideoCore")
	implementation project(":NRExoPlayerTracker")
	implementation project(':NRIMATracker')
}
```

### Install manually using source code

1. Clone this repo.
2. In your project, click **File > New > Import Module**.
3. Enter the location of the library module directory (located in the repo you just cloned) then click **Finish**.
4. Repeat steps 2 and 3 with all the modules you want to include.
5. In you app module's build.gradle file, add the following:

```
dependencies {
	...
	implementation project(":NewRelicVideoCore")
	implementation project(":NRExoPlayerTracker")
	implementation project(':NRIMATracker')
}
```
## Usage

To start the video agent with ExoPlayer tracker only:

<details>
<summary>Java</summary>
<p>

```Java
Integer trackerId = NewRelicVideoAgent.getInstance().start(new NRTrackerExoPlayer(player));
```

</p>
</details>
<details>
<summary>Kotlin</summary>
<p>

```Kotlin
val trackerId = NewRelicVideoAgent.getInstance().start(NRTrackerExoPlayer(player))
```

</p>
</details>

To start the video agent with ExoPlayer and IMA trackers:

<details>
<summary>Java</summary>
<p>

```Java
Integer trackerId = NewRelicVideoAgent.getInstance().start(new NRTrackerExoPlayer(player), new NRTrackerIMA());
```

</p>
</details>
<details>
<summary>Kotlin</summary>
<p>

```Kotlin
val trackerId = NewRelicVideoAgent.getInstance().start(NRTrackerExoPlayer(player), NRTrackerIMA())
```

</p>
</details>

## Documentation

To generate the javadocs, open the project in Android Studio and then go to `Tools > Generate JavaDoc...`, select `Whole Project`, then select the `Output directory` and click `OK`.

For more detail on the Events and Data Model generated by Video Agent for Android and for advanced concepts such as creating custom trackers, reference the [Advanced Topics](advanced.md) manual.

## Examples

The `app` folder contains a usage example that shows the basics of video tracking using ExoPlayer.

## Testing

The `Test` folder contains the test apps.

## Support

New Relic has open-sourced this project. This project is provided AS-IS WITHOUT WARRANTY OR DEDICATED SUPPORT. Issues and contributions should be reported to the project here on GitHub.

We encourage you to bring your experiences and questions to the [Explorers Hub](https://discuss.newrelic.com) where our community members collaborate on solutions and new ideas.

## Contributing

We encourage your contributions to improve New Relic Video Agent! Keep in mind when you submit your pull request, you'll need to sign the CLA via the click-through using CLA-Assistant. You only have to sign the CLA one time per project. If you have any questions, or to execute our corporate CLA, required if your contribution is on behalf of a company, please drop us an email at opensource@newrelic.com.

**A note about vulnerabilities**

As noted in our [security policy](../../security/policy), New Relic is committed to the privacy and security of our customers and their data. We believe that providing coordinated disclosure by security researchers and engaging with the security community are important means to achieve our security goals.

If you believe you have found a security vulnerability in this project or any of New Relic's products or websites, we welcome and greatly appreciate you reporting it to New Relic through [HackerOne](https://hackerone.com/newrelic).

## License

New Relic Video Agent is licensed under the [Apache 2.0](http://apache.org/licenses/LICENSE-2.0.txt) License.
