package com.example.nextlevel.ui.screens.onboarding.presentation

import com.example.nextlevel.ui.base.BaseEffect

sealed interface OnboardingEffect : BaseEffect {
	data object GoBack : OnboardingEffect
}