package com.tocura.study.kotlin.exceptions

import java.lang.RuntimeException

class TrainerNotFoundException(msg: String? = "trainer not found") : Exception(msg)