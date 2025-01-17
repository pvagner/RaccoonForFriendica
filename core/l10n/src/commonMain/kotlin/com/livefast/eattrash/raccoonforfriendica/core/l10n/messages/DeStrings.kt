package com.livefast.eattrash.raccoonforfriendica.core.l10n.messages

internal val DeStrings =
    object : DefaultStrings() {
        override val messageConfirmExit = "Tippen Sie zum Beenden erneut auf 🔙"
        override val messageAreYouSure = "Sind Sie sicher, dass Sie weitermachen wollen?"
        override val messageSuccess = "Operation erfolgreich abgeschlossen!"
        override val messageGenericError = "Ein unerwarteter Fehler ist aufgetreten"
        override val messageInvalidField = "Ungültiges Feld"
        override val messageMissingField = "Fehlendes Feld"
        override val messageEmptyList = "Hier gibt es nichts zu sehen 🗑️️"
        override val buttonConfirm = "Bestätigen Sie"
        override val buttonOk = "OK"
        override val buttonClose = "Schließen"
        override val buttonCancel = "Abbrechen"
        override val systemDefault = "System"
        override val settingsThemeLight = "Licht"
        override val settingsThemeDark = "Dunkelheit"
        override val settingsThemeBlack = "Schwarz (AMOLED)"
        override val sectionTitleHome = "Zeitleiste"
        override val sectionTitleExplore = "Erkundung"
        override val sectionTitleInbox = "Inbox"
        override val sectionTitleProfile = "Profil"
        override val barThemeOpaque = "Undurchsichtig"
        override val barThemeTransparent = "Transparent"
        override val timelineAll = "Alle"
        override val timelineSubscriptions = "Abonnements"
        override val timelineLocal = "Lokal"
        override val nodeVia = "via"
        override val timelineEntryInReplyTo = "in Beantwortung von"
        override val timelineEntryRebloggedBy = "erneut geteilt von"

        override fun accountFollower(count: Int) =
            when (count) {
                1 -> "Anhänger"
                else -> "Anhängern"
            }

        override fun accountFollowing(count: Int) =
            when (count) {
                1 -> "folgenden"
                else -> "folgende"
            }

        override val accountAge = "Kontenalter"
        override val dateYearShort = "j"
        override val dateMonthShort = "m"
        override val dateDayShort = "t"
        override val timeHourShort = "Std"
        override val timeMinuteShort = "Min"
        override val timeSecondShort = "s"
        override val accountSectionPosts = "Beiträge"
        override val accountSectionAll = "Beiträge und Antworten"
        override val accountSectionPinned = "Gepinnt"
        override val accountSectionMedia = "Medien"
        override val postTitle = "Beitrag"
        override val settingsTitle = "Einstellungen"
        override val settingsHeaderGeneral = "Allgemeines"
        override val settingsItemLanguage = "Sprache"
        override val settingsHeaderLookAndFeel = "Aussehen und Gefühl"
        override val settingsItemTheme = "UI-Thema"
        override val settingsItemFontFamily = "Schriftfamilie"
        override val settingsItemDynamicColors = "Material You"
        override val settingsItemDynamicColorsSubtitle =
            "eine Palette auf der Grundlage Ihres Hintergrunds erstellen"
        override val settingsItemThemeColor = "Thema Farbe"
        override val settingsItemThemeColorSubtitle =
            "wird nur angewendet, wenn „Material You“ deaktiviert ist"
        override val themeColorPurple = "Ozeanischer Oktopus"
        override val themeColorBlue = "Witziger Wal"
        override val themeColorLightBlue = "Desorientierter Delfin"
        override val themeColorGreen = "Fröhlicher Frosch"
        override val themeColorYellow = "Irrsinniger Igel"
        override val themeColorOrange = "Feuriger Fuchs"
        override val themeColorRed = "Knusprige Krabbe"
        override val themeColorPink = "Einzigartiges Einhorn"
        override val themeColorGray = "Wildgewordener Waschbär"
        override val themeColorWhite = "Brabbelnder Bär"
        override val messageUserUnlogged =
            "Sie müssen eingeloggt sein, um auf diesen Bereich zugreifen zu können 🪵"
        override val buttonLogin = "Anmeldung"
        override val fieldNodeName = "Instanzname"
        override val fieldUsername = "Username"
        override val fieldPassword = "Passwort"
        override val actionLogout = "Abmeldung"
        override val relationshipStatusFollowing = "Sie folgen"
        override val relationshipStatusFollowsYou = "Verfolgt Sie"
        override val relationshipStatusMutual = "Gegenseitige"
        override val relationshipStatusRequestedToOther = "Anfrage gesendet"
        override val relationshipStatusRequestedToYou = "Antrag anhängig"
        override val notificationTypeEntry = "veröffentlichte einen Beitrag"
        override val notificationTypeFavorite = "hat Ihren Beitrag zu den Favoriten hinzugefügt"
        override val notificationTypeFollow = "hat begonnen, Ihnen zu folgen"
        override val notificationTypeFollowRequest = "hat Ihnen eine Folgeanfrage geschickt"
        override val notificationTypeMention = "hat Sie erwähnt"
        override val notificationTypePoll =
            "eine Umfrage, an der Sie teilgenommen haben, wurde geschlossen"
        override val notificationTypeReblog = "hat Ihren Beitrag erneut geteilt"
        override val notificationTypeUpdate =
            "hat einen Beitrag aktualisiert, den Sie erneut geteilt haben"
        override val exploreSectionHashtags = "Hashtags"
        override val exploreSectionLinks = "Links"
        override val exploreSectionSuggestions = "Für Sie"

        override fun hashtagPeopleUsing(count: Int) =
            when (count) {
                1 -> "Person spricht darüber"
                else -> "Personen sprechen darüber"
            }

        override val feedTypeTitle = "Art der Fütterung"
        override val followerTitle = "Anhänger"
        override val followingTitle = "Folgende"
        override val actionFollow = "Folgen"
        override val actionUnfollow = "Entfolgen"
        override val actionDeleteFollowRequest = "Folgeanfrage abbrechen"
        override val sidebarAnonymousTitle = "Anonym"
        override val sidebarAnonymousMessage =
            "Willkommen bei Raccoon!\n\nDu kannst dich jederzeit über den Profilbildschirm in deine Instanz einloggen.\n\nViel Spaß mit Friendica!"
        override val bookmarksTitle = "Lesezeichen"
        override val favoritesTitle = "Favoriten"
        override val followedHashtagsTitle = "Verfolgte Hashtags"
        override val infoEdited = "bearbeitet"

        override fun extendedSocialInfoFavorites(count: Int) =
            when (count) {
                1 -> "Favorit"
                else -> "Favoriten"
            }

        override fun extendedSocialInfoReblogs(count: Int) =
            when (count) {
                1 -> "Aktie"
                else -> "Aktien"
            }

        override val actionMuteNotifications = "Benachrichtigungen stummschalten"
        override val createPostTitle = "Beitrag erstellen"
        override val messagePostEmptyText =
            "Bitte stellen Sie entweder einen Anhang oder eine Umfrage oder einen Text zur Verfügung"
        override val visibilityPublic = "Öffentlich"
        override val visibilityUnlisted = "Ungelistet"
        override val visibilityPrivate = "Nur Anhänger"
        override val visibilityDirect = "Nur Erwähnungen"
        override val createPostBodyPlaceholder = "Ihr toller neuer Beitrag… 🪄"
        override val createPostAttachmentsSection = "Anhänge"
        override val actionEdit = "Bearbeiten"
        override val pictureDescriptionPlaceholder = "Bildbeschreibung"
        override val insertLinkDialogTitle = "Link einfügen"
        override val insertLinkFieldAnchor = "Verankerung"
        override val insertLinkFieldUrl = "URL"
        override val selectUserDialogTitle = "Wählen Sie einen Benutzer"
        override val selectUserSearchPlaceholder = "Benutzername oder Handle"
        override val searchSectionUsers = "Benutzer"
        override val searchPlaceholder = "Suche im Fediversum"
        override val messageSearchInitialEmpty = "Beginnen Sie etwas zu tippen"
        override val topicTitle = "Thema"
        override val threadTitle = "Gewinde"
        override val buttonLoadMoreReplies = "Weitere Antworten laden"
        override val postSensitive = "Sensibler Inhalt"
        override val actionCreateThreadInGroup = "Beitrag an"
        override val settingsHeaderNsfw = "NSFW"
        override val settingsItemIncludeNsfw = "NSFW-Inhalte einbeziehen"
        override val settingsItemBlurNsfw = "Unschärfe NSFW Medien"
        override val settingsItemDefaultTimelineType = "Standard-Zeitleisten-Typ"
        override val actionDelete = "Löschen"
        override val actionShare = "Aktie"
        override val actionCopyUrl = "Link kopieren"
        override val messageTextCopiedToClipboard = "In die Zwischenablage kopiert! 📋"
        override val contentScaleFit = "Aspekt fit"
        override val contentScaleFillWidth = "Breite der Füllung"
        override val contentScaleFillHeight = "Höhe der Füllung"
        override val contentScaleTitle = "Skalenmodus"
        override val shareAsUrl = "Als URL freigeben"
        override val shareAsFile = "Als Datei freigeben"
        override val actionMute = "Stummschalten"
        override val actionUnmute = "Stummschaltung aufheben"
        override val settingsItemBlockedAndMuted = "Filter verwalten"
        override val manageBlocksSectionMuted = "Stummgeschalteter"
        override val manageBlocksSectionBlocked = "Gesperrter"
        override val actionBlock = "Sperren"
        override val actionUnblock = "Sperre aufheben"
        override val loginMethodBasic = "Legacy"
        override val actionPin = "An das Profil anheften"
        override val actionUnpin = "Anheftung an das Profil aufheben"
        override val settingsSectionDebug = "Debuggen"
        override val settingsAbout = "Informationen zur App"
        override val settingsAboutAppVersion = "Version"
        override val settingsAboutChangelog = "Änderungsliste"
        override val settingsAboutViewGithub = "Ansicht auf GitHub"
        override val settingsAboutReportIssue = "Ein Problem melden"
        override val settingsAboutViewFriendica = "Ansicht auf Friendica"
        override val manageCirclesTitle = "Kreise"
        override val createCircleTitle = "Kreis erstellen"
        override val editCircleTitle = "Kreis bearbeiten"
        override val circleEditFieldName = "Name"
        override val circleAddUsersDialogTitle = "Benutzer zum Hinzufügen auswählen"
        override val visibilityCircle = "Kreis"
        override val selectCircleDialogTitle = "Wählen Sie einen Kreis"
        override val messagePostInvalidVisibility =
            "Bitte wählen Sie eine gültige Sichtbarkeitsoption"
        override val settingsItemFontScale = "Schriftgröße"
        override val fontScaleNormal = "Normal"
        override val fontScaleSmaller = "Kleiner"
        override val fontScaleSmallest = "Kleinste"
        override val fontScaleLarger = "Größer"
        override val fontScaleLargest = "Größte"
        override val settingsItemUrlOpeningMode = "URL-Öffnungsmodus"
        override val urlOpeningModeExternal = "Externer Browser"
        override val urlOpeningModeCustomTabs = "Benutzerdefinierte Registerkarten"
        override val urlOpeningModeInternal = "Interne Webansicht"
        override val dialogErrorTitle = "Ups…"
        override val messagePollVoteErrorBody =
            "Leider bin ich nur ein mobiler Entwickler und kann keine fehlenden Backend-Methoden hinzufügen!\nSchauen Sie sich dieses Problem an und setzen Sie ein 👍, damit die Entwickler wissen, dass es sich lohnen könnte, es zu implementieren."
        override val buttonPollErrorOpenIssue = "Ansicht auf GitHub"
        override val actionVote = "Wählen Sie"
        override val pollExpired = "Abgelaufen"
        override val shortUnavailable = "K.A."

        override fun pollVote(count: Int): String =
            when (count) {
                1 -> "Stimme"
                else -> "Stimmen"
            }

        override val pollExpiresIn = "Läuft ab in"
        override val actionHideResults = "Ergebnisse anzeigen"
        override val actionShowResults = "Ergebnisse ausblenden"
        override val inboxConfigureFilterDialogTitle = "Filter konfigurieren"
        override val inboxConfigureFilterDialogSubtitle =
            "Wenn ein Wert abgewählt ist, gibt der Filter nur ungelesene Elemente zurück."
        override val notificationTypeEntryName = "Benachrichtigungen veröffentlichen"
        override val notificationTypeFavoriteName = "Favoriten"
        override val notificationTypeFollowName = "Neue Follower"
        override val notificationTypeFollowRequestName = "Anfragen folgen"
        override val notificationTypeMentionName = "Erwähnungen & Antworten"
        override val notificationTypePollName = "Umfragen"
        override val notificationTypeReblogName = "Aktien"
        override val notificationTypeUpdateName = "Aktualisierungen"
        override val muteDurationIndefinite = "Unbestimmt"
        override val selectDurationDialogTitle = "Dauer auswählen"
        override val muteDurationItem = "Sie werden keine Beiträge von diesem Benutzer sehen für"
        override val muteDisableNotificationsItem = "Benachrichtigungen deaktivieren"
        override val actionSendFollowRequest = "Anfrage senden"
        override val postBy = "von"
        override val customOption = "Benutzerdefiniert"
        override val colorPickerDialogTitle = "Wählen Sie eine Farbe"
        override val followRequestsTitle = "Anfragen folgen"
        override val actionAccept = "Akzeptieren"
        override val actionReject = "Ablehnen"
        override val actionHideContent = "Inhalt ausblenden"
        override val messageEmptyInbox =
            "🎉 Sie haben alles aufgeholt! 🎉\n\nNeue Benachrichtigungen werden auf dieser Seite angezeigt, sobald sie eintreffen."
        override val createPostSpoilerPlaceholder = "Spoiler-Text"
        override val createPostTitlePlaceholder = "Titel (fakultativ)"
        override val actionSwitchAccount = "Konto wechseln"
        override val actionDeleteAccount = "Konto löschen"
        override val editProfileTitle = "Profil bearbeiten"
        override val editProfileSectionPersonal = "Persönliche Daten"
        override val editProfileItemDisplayName = "Name anzeigen"
        override val editProfileItemBio = "Bio"
        override val editProfileSectionFlags = "Sichtbarkeit & Berechtigungen"
        override val editProfileItemBot = "Ich bin ein Bot"
        override val editProfileItemLocked = "Manuelle Genehmigung für Folgeanträge erforderlich machen"
        override val editProfileItemDiscoverable = "In der Suche auffindbar machen"
        override val editProfileItemNoIndex = "Meine Beiträge von öffentlichen Zeitleisten ausschließen"
        override val unsavedChangesTitle = "Nicht gespeicherte Änderungen"
        override val messageAreYouSureExit = "Sind Sie sicher, dass Sie aussteigen wollen?"
        override val buttonSave = "Speichern Sie"
        override val editProfileSectionFields = "Benutzerdefinierte Felder (experimentell)"
        override val editProfileItemFieldKey = "Schlüssel"
        override val editProfileItemFieldValue = "Wert"
        override val editProfileSectionImages = "Bilder (experimentell)"
        override val editProfileItemAvatar = "Avatar"
        override val editProfileItemHeader = "Banner"
        override val nodeInfoTitle = "Server-Informationen"
        override val nodeInfoSectionRules = "Regeln"
        override val nodeInfoSectionContact = "Kontakt"
        override val actionAddNew = "Neu hinzufügen"
        override val directMessagesTitle = "Direkte Nachrichten"

        override fun messages(count: Int): String =
            when (count) {
                1 -> "Meldunge"
                else -> "Meldungen"
            }

        override val messageEmptyConversation =
            "Dies ist der Beginn Ihres epischen Gesprächs mit"
        override val followRequiredMessage =
            "Sie müssen dem anderen Nutzer folgen, um eine direkte Nachricht senden zu können!"
        override val galleryTitle = "Galerie"

        override fun items(count: Int): String =
            when (count) {
                1 -> "Element"
                else -> "Elemente"
            }

        override val galleryFieldAlbumName = "Name des Albums"
        override val messageEmptyAlbum =
            "Es sieht so aus, als ob dieses Album noch leer ist… ✨"
        override val actionMove = "Verschieben"
        override val pickFromGalleryDialogTitle = "Wählen Sie aus der Galerie"

        override fun unreadMessages(count: Int): String = "ungelesen"

        override val messageCharacterLimitExceeded =
            "Sie haben die maximal zulässige Zeichenanzahl überschritten"
        override val userFieldPersonalNote = "Persönliche Anmerkung"
        override val actionEditPersonalNote = "Anmerkung bearbeiten"
        override val actionCancelEditPersonalNote = "Abbrechen Anmerkung bearbeiten"
        override val messageVideoNsfw = "Dieses Video kann sensible Inhalte enthalten 🙈"
        override val buttonLoad = "Laden"
        override val messageAreYouSureReblog =
            "Dieser Beitrag ist mehr als einen Monat alt. Sind Sie sicher, dass Sie ihn erneut teilen möchten?"
        override val unpublishedTitle = "Unveröffentlichte Artikel"
        override val unpublishedSectionScheduled = "Geplante"
        override val unpublishedSectionDrafts = "Entwürfe"
        override val actionSetScheduleDate = "Zeitplan festlegen"
        override val actionUpdateScheduleDate = "Zeitplan ändern"
        override val actionPublishDefault = "Jetzt veröffentlichen"
        override val scheduleDateIndication = "Geplant für:"
        override val messageScheduleDateInThePast = "Bitte wählen Sie einen Termin in der Zukunft"
        override val actionSaveDraft = "Entwurf speichern"
        override val settingsItemDefaultPostVisibility = "Standardsichtbarkeit für Beiträge"
        override val settingsItemDefaultReplyVisibility = "Standardsichtbarkeit für Antworten"
        override val reportCategoryLegal = "Rechtliche Fragen"
        override val itemOther = "Sonstiges"
        override val reportCategorySpam = "Spam"
        override val reportCategoryViolation = "Verletzung der Serverregeln"
        override val messageMissingRules = "Bitte wählen Sie mindestens eine Regel aus"
        override val createReportTitleUser = "Melden"
        override val createReportTitleEntry = "Beitrag melden von"
        override val createReportItemCategory = "Kategorie"
        override val createReportCommentPlaceholder = "Bitte beschreiben Sie das Problem, das Sie hatten"
        override val createReportItemRules = "Verstöße gegen Vorschriften"

        override fun createReportSelectedRules(count: Int) =
            when (count) {
                1 -> "Regel ausgewählt"
                else -> "Regeln ausgewählt"
            }

        override val createReportItemForward = "Bericht vorwärts"
        override val actionReportUser = "Benutzer melden"
        override val actionReportEntry = "Beitrag melden"
        override val actionViewDetails = "Einzelheiten"
        override val actionAddImage = "Bild hinzufügen"
        override val actionAddImageFromGallery = "Bild hinzufügen (Galerie)"
        override val actionAddPoll = "Umfrage hinzufügen"
        override val actionRemovePoll = "Umfrage entfernen"
        override val createPostPollSection = "Umfrage"
        override val createPostPollOptionLabel = "Option"
        override val createPostPollItemMultiple = "Mehrfachauswahl zulassen"
        override val createPostPollItemExpirationDate = "Verfallsdatum"
        override val messageInvalidPollError =
            "Ungültige Umfrage, bitte überprüfen Sie die Optionen und das Verfallsdatum"
        override val userFeedbackFieldEmail = "E-Mail oder Benutzername (optional)"
        override val userFeedbackFieldComment = "Feedback"
        override val userFeedbackCommentPlaceholder =
            "Beschreiben Sie das aufgetretene Problem oder hinterlassen Sie einfach ein Feedback 🖋️"
        override val changeNodeDialogTitle = "Instanz ändern"
        override val actionQuote = "Zitieren"
        override val actionAddSpoiler = "Spoiler hinzufügen"
        override val actionRemoveSpoiler = "Spoiler entfernen"
        override val actionAddTitle = "Titel hinzufügen"
        override val actionRemoveTitle = "Titel entfernen"
        override val actionRevealContent = "Inhalt enthüllen"
        override val settingsItemExcludeRepliesFromTimeline = "Antworten aus der Zeitleiste ausschließen"
        override val insertEmojiTitle = "Emoji einfügen"
        override val messageLoadingUsers = "Benutzer laden"
        override val actionOpenPreview = "Vorschau öffnen"
        override val actionSwitchToClassicMode = "Zum klassischen Modus wechseln"
        override val actionSwitchToForumMode = "Zum Forum-Modus wechseln"
        override val settingsItemOpenGroupsInForumModeByDefault =
            "Gruppen standardmäßig im Forum-Modus öffnen"
        override val actionInsertList = "Liste einfügen"
        override val actionDismissAllNotifications = "Alle Benachrichtigungen ablehnen"
        override val settingsItemMarkupMode = "Markierung für Compositing"
        override val markupModePlainText = "Einfacher Text"
        override val messageAltTextMissingError =
            "Einige Anhänge haben keinen alternativen Text, fügen Sie ihn aus Gründen der Zugänglichkeit ein."
        override val buttonPublishAnyway = "Trotzdem veröffentlichen"
        override val actionCopyToClipboard = "In die Zwischenablage kopieren"
        override val actionSaveToCalendar = "Im Kalender speichern"
        override val settingsItemMaxPostBodyLines = "Maximale Zeilenzahl für Beitragstext"
        override val settingsOptionUnlimited = "Unbegrenzt"
        override val settingsAboutLicences = "Lizenzen"
        override val settingsOptionBackgroundNotificationCheck =
            "Prüfung auf Benachrichtigungen im Hintergrund"
        override val settingsSubtitleBackgroundNotificationRestricted =
            "Aktivieren Sie die Hintergrundaktivität für die Anwendung in den Systemeinstellungen"
        override val settingsSubtitleBackgroundNotificationNotRestricted =
            "Keine Systembeschränkung für Hintergrundaktivitäten "
        override val durationNever = "Niemals"
        override val unreadNotificationTitle = "Überprüfen Sie Ihre Benachrichtigungen!"

        override fun unreadNotificationBody(count: Int) =
            when (count) {
                1 -> "Es gibt $count Artikel in der 🗑️"
                else -> "Es gibt $count Artikel in der 🗑️"
            }

        override val settingsAboutUserManual = "Benutzerhandbuch"
        override val editProfileItemHideCollections = "Follower und Followerlisten privat machen"
        override val settingsAboutMatrix = "Matrix-Raum beitreten"
        override val settingsAutoloadImages = "Modus für das Laden von Bildern"
        override val circleTypeGroup = "Gruppen"
        override val circleTypePredefined = "Kanäle"
        override val circleTypeUserDefined = "Ihre Listen"
        override val settingsItemNotificationMode = "Benachrichtigungsmodus"
        override val settingsNotificationModeDisabled = "Deaktiviert"
        override val settingsNotificationModePull = "ziehen"
        override val settingsNotificationModePullExplanation = "im Hintergrund"
        override val settingsNotificationModePush = "push"
        override val settingsNotificationModePushExplanation = "UnifiedPush"
        override val settingsItemPushNotificationState = "Status der Push-Benachrichtigung"
        override val settingsPushNotificationStateUnsupported = "Nicht unterstützt"
        override val settingsPushNotificationStateInitializing = "Initialisierung…"
        override val settingsPushNotificationStateNoDistributors = "Keine Verteiler verfügbar"
        override val settingsPushNotificationStateNoDistributorSelected = "Verteiler auswählen"
        override val settingsPushNotificationStateIdle = "Leerlauf"
        override val settingsPushNotificationStateEnabled = "Aktiv"
        override val experimental = "experimentell"
        override val loginTitle = "🦝 Willkommen! 🦝"
        override val loginSubtitle =
            "Melden Sie sich bei einer Instanz an, um anderen Personen zu folgen, neue Beiträge oder Antworten zu erstellen und Ihre Favoriten oder Lesezeichen zu verwalten."
        override val moreInfo = "Mehr entdecken"
        override val loginMoreInfoBottomSheetContent =
            "Der Anmeldevorgang beginnt in der App mit der Auswahl der Instanz. " +
                "Danach setzen Sie den OAuth2-Vorgang in einem Webbrowser fort." +
                "\n\nWenn Sie noch kein Konto haben, wählen Sie bitte eine Instanz und melden Sie sich zuerst in Ihrem Browser an."
        override val loginFriendicaHeader = "Ich bin bei Friendica"
        override val loginMastodonHeader = "Ich bin bei Mastodon"
        override val helpMeChooseAnInstance = "Hilf mir bei der Auswahl einer Instanz"
        override val exempliGratia = "z. B."
        override val newAccountTitle = "Konto hinzufügen"
        override val imageLoadingModeAlways = "Immer"
        override val imageLoadingModeOnDemand = "Bei Bedarf"
        override val imageLoadingModeOnWiFi = "Im WiFi"
        override val messageReplyVisibilityGreaterThanParentError =
            "Sie veröffentlichen eine Antwort mit einer breiteren Sichtbarkeit als der ursprüngliche Beitrag"
        override val settingsItemCrashReportEnabled = "Anonyme Absturzberichte aktivieren"
        override val messageRestartToApplyChanges =
            "Bitte starten Sie die Anwendung neu, um die Änderungen zu übernehmen"
        override val settingsItemHideNavigationBarWhileScrolling =
            "Navigationsleiste beim Scrollen ausblenden"
        override val settingsItemAppIcon = "Anwendungssymbol"
        override val appIconDefault = "Standard"
        override val appIconClassical = "Klassisch"
        override val settingsItemExport = "Einstellungen exportieren"
        override val settingsItemImport = "Einstellungen importieren"
        override val actionExport = "Exportieren"
        override val actionChangeMarkupMode = "Auszeichnungsart ändern"
        override val confirmChangeMarkupMode =
            "Wenn Sie den Markup-Typ ändern, gehen alle Formatierungen verloren. Trotzdem weitermachen?"
        override val actionEditMembers = "Mitglieder bearbeiten"
        override val settingsItemBarTheme = "Thema der Status- und Navigationsleiste"
        override val barThemeSolid = "Solide"
        override val announcementsTitle = "Ankündigungen"
        override val actionGoBack = "Zurückgehen"
        override val actionOpenOptions = "Optionen"
        override val actionAddReaction = "Reaktion hinzufügen"
        override val actionShowContentDescription = "Beschreibung anzeigen"
        override val actionReblog = "Erneut freigeben"
        override val actionToggleReveal = "Sichtbarkeit umschalten"
        override val actionOpenFullScreen = "Vollbild öffnen"
        override val actionSubmit = "Senden"
        override val actionChangeVisibility = "Sichtbarkeit ändern"
        override val actionSave = "Speichern"
        override val actionReply = "Antwort"
        override val actionSelect = "Wert auswählen"
        override val actionOpenSideMenu = "Seitenmenü öffnen"
        override val actionOpenDetail = "Detail öffnen"
        override val actionOpenLink = "Link öffnen"
        override val actionClear = "löschen"
        override val actionSearch = "Suche"
        override val actionFilter = "Filtern"
        override val actionAddToBookmarks = "Zu den Lesezeichen hinzufügen"
        override val actionAddToFavorites = "Zu den Favoriten hinzufügen"
        override val actionDownload = "Herunterladen"
        override val actionInsertLink = "Link einfügen"
        override val verifiedField = "geprüft"
        override val updateDate = "Aktualisierungsdatum"
        override val creationDate = "Erstellungsdatum"
        override val likesCount = "gefällt"
        override val dislikesCount = "gefällt nicht"
        override val highestScore = "höchste Punktzahl"
        override val formatBold = "Fettdruck"
        override val formatItalic = "Kursiv"
        override val formatStrikethrough = "Durchgestrichen"
        override val formatUnderlined = "Unterstrichen"
        override val formatMonospace = "Einfarbig"
    }
