<div align="center">
  <img alt="badge for Kotlin" src="https://img.shields.io/badge/Kotlin-2.0.21-7F52FF?logo=kotlin" />
  <img alt="badge for Gradle" src="https://img.shields.io/badge/Gradle-8.8-02303A?logo=gradle" />
  <img alt="badge for Android" src="https://img.shields.io/badge/Android-26+-34A853?logo=android" />
  <img alt="badge for Compose Multiplatform" src="https://img.shields.io/badge/Compose-1.7.0-4285F4?logo=jetpackcompose" />
  <img alt="badge for project license" src="https://img.shields.io/github/license/LiveFastEatTrashRaccoon/RaccoonForFriendica" />
  <img alt="badge for build status" src="https://github.com/LiveFastEatTrashRaccoon/RaccoonForFriendica/actions/workflows/build.yml/badge.svg" />
  <img alt="badge for unit test status" src="https://github.com/LiveFastEatTrashRaccoon/RaccoonForFriendica/actions/workflows/unit_tests.yml/badge.svg" />
  <a href="https://livefasteattrashraccoon.github.io/RaccoonForFriendica" target="_blank"><img alt="badge for website" src="https://img.shields.io/badge/website-9812db" /></a>
  <a href="https://livefasteattrashraccoon.github.io/RaccoonForFriendica/manual/main.html" target="_blank"><img alt="badge for user manual" src="https://img.shields.io/badge/user%20manual-3c00c7" /></a>
</div>

<div align="center">
  <img alt="application icon" src="https://github.com/LiveFastEatTrashRaccoon/RaccoonForFriendica/blob/master/composeApp/src/androidMain/res/mipmap-xxxhdpi/ic_launcher.webp" width="250" height="auto" />
</div>

# RaccoonForFriendica

This is a client for the [Friendica](https://friendi.ca) federated social platform powered by
Kotlin Multiplatform (KMP) and Compose Multiplatform (CMP). The reference platform is currently
Android.

The project is _heavily_ inspired by the **RaccoonForLemmy** app for Lemmy, which demonstrated
something like this can be achieved with this tech stack. This is why it retains part of its name,
making clear that the two projects are related and share a lot of common heritage and are maintained
by the same people.

## Want to try it?

Here are some options to install the application on your device. The best way to install testing APKs
is Obtainium, please insert this repository's URL as a source.

```
https://github.com/LiveFastEatTrashRaccoon/RaccoonForFriendica
```

<div align="center">
  <div style="display: flex; flex-flow: row wrap; justify-content: center; align-items: center;">
    <a href="https://github.com/ImranR98/Obtainium/releases">
    <img alt="Get it on Obtainium banner" width="200" src="https://github.com/user-attachments/assets/1501ad39-c581-4760-96ce-6d5181ab6207" />
    </a>
  </div>
</div>

> [!TIP]
> Make sure to check the "Include pre-releases" option (in order to receive all alpha and beta
> builds):

## Screenshots

<div align="center">
  <img alt="timeline screen with subscriptions" width="310" src="https://livefasteattrashraccoon.github.io/RaccoonForFriendica/manual/images/timeline.png" />
  <img alt="user detail screen" width="310" src="https://livefasteattrashraccoon.github.io/RaccoonForFriendica/manual/images/user_detail.png" />
  <img alt="post detail screen" width="310" src="https://livefasteattrashraccoon.github.io/RaccoonForFriendica/manual/images/post_detail.png" />
  <img alt="list of posts containing a followed hashtag" width="310" src="https://livefasteattrashraccoon.github.io/RaccoonForFriendica/manual/images/hashtag_feed.png" />
  <img alt="post editor" width="310" src="https://livefasteattrashraccoon.github.io/RaccoonForFriendica/manual/images/composer.png" />
  <img alt="settings screen" width="310" src="https://livefasteattrashraccoon.github.io/RaccoonForFriendica/manual/images/settings.png" />
  <img alt="explore screen on hashtags tab" width="310" src="https://livefasteattrashraccoon.github.io/RaccoonForFriendica/manual/images/explore_hashtags.png" />
  <img alt="notifications screen" width="310" src="https://livefasteattrashraccoon.github.io/RaccoonForFriendica/manual/images/inbox.png" />
  <img alt="group opened in forum mode" width="310" src="https://livefasteattrashraccoon.github.io/RaccoonForFriendica/manual/images/forum_list.png" />
  <img alt="thread screen within a group with replies" width="310" src="https://livefasteattrashraccoon.github.io/RaccoonForFriendica/manual/images/thread_detail.png" />
</div>

## Why was the project started and why is it called like that?

Because raccoons are so adorable, aren't they? 🦝🦝🦝

Joking apart, one of the main goals was to experiment with Kotlin multiplatform (KMP) and Compose
Multiplatform (CMP). This project has a fair degree of complexity and allows to put under stress
the existing solutions and evaluate what they behave like with common tasks (image loading, HTML
rendering, file system and gallery access, networking, local database management, shared
preferences, navigation, access to resources like fonts/drawables/localization, etc.).

Secondly, the Android ecosystem for Friendica was a little lacking, especially with few native
apps (except [Dica](https://github.com/jasoncheng/dica),
whereas [Friendiqa](https://git.friendi.ca/lubuwest/Friendiqa) is a very _cute_ example of Qt on
Android, although not a traditional mobile app at all).
I ❤️ Kotlin, I ❤️ Free and Open Source Software and I ❤️ native app development, so there was a
niche that could be filled.

In the third place, we were wondering whether the adoption of a platform like Friendica could be
improved with a user-friendly and easy to use mobile app that abstracts away some of the
complications of the current web UI. This is why this app looks a lot like a plain Mastodon client
(and technically speaking _it is_ a Mastodon client) – adding on top of it some Friendica specific
features such as media gallery, circle management, direct messages and support for ActivityPub
groups.

With this respect, it takes a different approach compared to other existing solutions
like the feature-complete [Relatica](https://gitlab.com/mysocialportal/relatica) app, in trying
to look familiar to users with a UI they are already accustomed to, adding a thin layer on
top of it to support the additional features.

This project is all about experimenting and learning, so please be _patient_ if you find some bugs
or missing features.

## Development roadmap

The app is under ongoing development, here is a list of the features that are being implemented:

- [x] timeline view (Public/Local/Subscriptions) with ability to switch feed type
- [x] post detail, i.e. opening a conversation or reply in its context
- [x] user detail with ability to see posts/post and replies/pinned/media posts
- [x] login/logout to one's own instance
- [x] view notifications and check for unread items
- [x] view one's own profile
- [x] see trending posts/hashtags/news
- [x] see following recommendations
- [x] view all the posts containing a given hashtag
- [x] view followers and following of a given user
- [x] follow/unfollow other users
- [x] post actions (re-share, favorite, bookmark)
- [x] follow/unfollow hashtags
- [x] view list of one's own favorites
- [x] view list of one's own bookmarks
- [x] view list of one's own followed hashtags
- [x] view people who added as favorite/re-shared a given post
- [x] enable/disable notifications for other users
- [x] create a post/reply with image attachments and alt text
- [x] global search
- [x] open groups in "forum mode" (Friendica-specific)
- [x] delete one's own posts
- [x] edit one's own posts
- [x] mute/unmute user and see list of muted users
- [x] block/unblock user and see list of blocked users
- [x] OAuth and HTTP Basic authentication modes
- [x] pin/unpin status to profile
- [x] manage one's own circles/following lists
- [x] polls (read-only, voting is not supported yet by the back-end)
- [x] manage follow requests
- [x] support for post addons (spoilers and titles)
- [x] multi-account
- [x] edit one's own profile (custom fields and images not supported yet by the back-end)
- [x] direct messages (Friendica-specific)
- [x] photo gallery (Friendica-specific)
- [x] advanced media visualization (videos, GIFs, multiple images)
- [x] scheduled posts and local drafts
- [x] create reports for posts and users 
- [x] custom emojis
- [x] event calendar (Friendica-specific, creating events is not supported yet by the back-end)
- [x] pull notifications with background checks
- [x] (experimental) push notifications using UnifiedPush

## Technologies used

- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform) for UI
- [Room](https://developer.android.com/kotlin/multiplatform/room) for local persistence
- [Koin](https://insert-koin.io/) for dependency injection
- [Voyager](https://voyager.adriel.cafe/) for navigation
- [Ktor](https://ktor.io/) and [Ktorfit](https://foso.github.io/Ktorfit) for networking
- [Lyricist](https://github.com/adrielcafe/lyricist) for l10n
- [Multiplatform settings](https://github.com/russhwolf/multiplatform-settings) for encrypted
  preferences
- [MaterialKolor](https://github.com/jordond/MaterialKolor) for custom theme generation
- [Ksoup](https://github.com/MohamedRejeb/Ksoup) for HTML parsing
- [Colorpicker-compose](https://github.com/skydoves/colorpicker-compose) for custom color selection
- [Sentry](https://sentry.io) for crash reporting and user feedback collection
- [UnifiedPush](https://unifiedpush.org) for push notifications

## Want to leave your feedback or report a bug?

- use the "Report an issue" in-app form you can find in the "App information" dialog;
- follow the [Friendica group](https://poliverso.org/profile/raccoonforfriendicaapp) to receive
  updates about the new releases and participate in public discussions;
- join our [Matrix room](https://matrix.to/#/#raccoonforfriendicaapp:matrix.org) to reach out to the
  developers or other users;
- use the
  GitHub [issue tracker](https://github.com/LiveFastEatTrashRaccoon/RaccoonForFriendica/issues)
  to report bugs or request features;
- finally, if none of the above methods fits your needs you
  can write an [email](mailto://livefast.eattrash.raccoon@gmail.com).

## Disclaimer

> [!WARNING]
> This is an experimental project and some technologies it is build upon are still in pre-production
> stage, moreover this is a side-project developed by volunteers in their spare time, so use
> _at your own risk_.

You shouldn't expect a full-fledged and fully functional app; you should be prepared to occasional
failures, yet-to-implement features and areas where (a lot of) polish is needed. Contributions are
welcome and new feature requests (outside the agreed roadmap) will be evaluated depending on the
available time.
