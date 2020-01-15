package com.example.notekeeper

object DataManager {

    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    const val ANDROID_INTENTS = "android_intents"
    const val ANDROID_ASYNC = "android_async"
    const val JAVA_FUNDAMENTALS_LANGUAGE = "java_lang"
    const val JAVA_FUNDAMENTALS_CORE = "java_core"

    const val NOTE_TEXT = """Lorem Ipsum is simply dummy text of the printing and typesetting industry."""

    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeCourses() {
        var course = CourseInfo(ANDROID_INTENTS, "Android Programming with Intents")
        courses.set(course.courseId, course)

        course = CourseInfo(ANDROID_ASYNC, "Android Async Programming and Services")
        courses.set(course.courseId, course)

        course = CourseInfo(JAVA_FUNDAMENTALS_LANGUAGE, "Java Fundamentals: The Java Language")
        courses.set(course.courseId, course)

        course = CourseInfo(JAVA_FUNDAMENTALS_CORE, "Java Fundamentals - The Core Platform")
        courses.set(course.courseId, course)
    }

    private fun initializeNotes() {
        var noteInfo = NoteInfo(courses.get(ANDROID_INTENTS), "First note", "1. " + NOTE_TEXT)
        notes.add(noteInfo)

        noteInfo = NoteInfo(courses.get(ANDROID_ASYNC), "Second note", "2. " + NOTE_TEXT)
        notes.add(noteInfo)

        noteInfo = NoteInfo(courses.get(JAVA_FUNDAMENTALS_LANGUAGE), "Third note", "3. " + NOTE_TEXT)
        notes.add(noteInfo)

        noteInfo = NoteInfo(courses.get(JAVA_FUNDAMENTALS_CORE), "Fourth note", "4. " + NOTE_TEXT)
        notes.add(noteInfo)
    }

}