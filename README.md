# Attendance Monitor (**Still In Progress**)

## Description

Simple Android app that facilitate the task of keeping track of student how are absent or present, by using technoloy
like Qr Code.

The app usage could by generalized as a relation between supervision (professor) with co-worker (student), or handling meetings sessions .... (Possibilities are endless).

##### Professor
A Professor could create a classroom and generate invitation code for student to join in.

For each session the professor generate a QrCode that he may project to a screen or a projector (QrCode is valid for a specific amount of time).

The app keep track of students who are present / abscent and generate excel / csv repost of all classroom maintaned by the professor.

##### Student
Every student is required to scan the code by the app to validate his presence during the session.

A Student who misses a session could justify his absence by providing a justification (may include a document) for the professor to validate or refuse.

## Requirement / Dependencies

Technology or dependencies used for building the app:

* Database: Firebase
* Generating Qr Code : Zxing
* Design : Material Desing

Dependencies in gradle
```
    implementation 'com.mikhaellopez:circularimageview:4.2.0'
    implementation 'com.google.android.material:material:1.1.0'
    implementation 'com.android.support:cardview-v7:28.0.0'
    implementation 'com.google.firebase:firebase-analytics:17.2.2'
    implementation 'com.google.firebase:firebase-firestore:21.4.3'
    implementation 'com.google.zxing:core:3.2.1'
```

## Overview

![profile](.img/profile.png)

![qrcode](.img/qrcode.png)

![classroom](.img/classroom.png)

## Todo

- [ ] Statistic UI and support
- [ ] Justification implementation ( UI / Backend)
- [ ] Remove classroom by swiping
- [ ] Student Support (Front / back)
- [ ] Server Side : Manage invitation codes for classrooms
