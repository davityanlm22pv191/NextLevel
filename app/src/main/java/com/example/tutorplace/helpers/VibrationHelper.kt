package com.example.tutorplace.helpers

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.VibratorManager
import androidx.annotation.RequiresApi

object VibrationHelper {

	@RequiresApi(Build.VERSION_CODES.S)
	fun performTick(context: Context) {
		val vb = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
		val vibrator = vb.defaultVibrator
		if (!vibrator.hasVibrator()) return
		val primitive = VibrationEffect.Composition.PRIMITIVE_TICK
		if (vibrator.areAllPrimitivesSupported(primitive)) {
			vibrator.vibrate(
				VibrationEffect.startComposition()
					.addPrimitive(primitive)
					.compose()
			)
		}
		//  Можно для эксперимента попробовать как на устройствах бюджетных будет вибрация
		//  Мне она показалась сильной, в отличие от PRIMITIVE_TICK, поэтому я не стал добавлять
		// else {
		// vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
		// }
	}
}