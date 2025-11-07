package com.example.tutorplace.ui.common

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun SkeletonShimmer(
	modifier: Modifier = Modifier,
	content: @Composable () -> Unit
) {
	// 🔁 1. Создаём бесконечную анимацию
	// rememberInfiniteTransition запоминает состояние анимации и
	// позволяет нам циклически обновлять её значения (в данном случае — смещение градиента)
	val transition = rememberInfiniteTransition(label = "shimmer")

	// 📈 2. Анимируем значение "translateAnim" — это просто число (Float),
	// которое будет постоянно меняться от 0 до 1000 и снова возвращаться к 0.
	// Оно отвечает за "положение" нашего блика (градиента).
	val translateAnim = transition.animateFloat(
		initialValue = 0f,       // начальное значение (слева)
		targetValue = 1000f,     // конечное значение (справа)
		animationSpec = infiniteRepeatable( // бесконечное повторение
			animation = tween(
				durationMillis = 1000,     // скорость движения shimmer-а
				easing = FastOutLinearInEasing      // равномерное движение без ускорений
			),
			repeatMode = RepeatMode.Reverse // когда доходит до конца — начинается заново
		),
		label = "translate"
	)

	// 🎨 3. Задаём цвета для shimmer-а (можно подбирать под стиль)
	// Это три цвета: тёмный → светлый → тёмный.
	// В центре — яркий цвет, создающий "блик".

	val shimmerColors = listOf(
		Color.LightGray.copy(alpha = 0.6f),  // тёмный участок
		Color.Gray.copy(alpha = 0.3f),       // светлая полоса
		Color.LightGray.copy(alpha = 0.6f)   // снова тёмный участок
	)

	// 🖌️ 4. Создаём Brush (кисть) — линейный градиент.
	// Он будет использовать shimmerColors и двигаться по диагонали (start → end).
	// Значения Offset зависят от translateAnim, поэтому градиент двигается во времени.
	val brush = Brush.linearGradient(
		colors = shimmerColors,
		start = Offset(translateAnim.value - 1000f, 0f), // смещение влево
		end = Offset(translateAnim.value, 1000f)         // смещение вправо и вниз
	)

	// 📦 5. Оборачиваем контент в Box, чтобы применить эффект поверх него.
	// modifier.graphicsLayer(alpha = 0.99f) нужен, чтобы "включить" аппаратный слой
	// (иначе drawWithCache иногда не сработает).
	Box(
		modifier = modifier
			.drawWithCache {
				// ⚙️ drawWithCache позволяет кэшировать всё, что не меняется каждый кадр.
				// Здесь мы создаём лямбду onDrawWithContent, которая вызывается при каждой отрисовке.
				onDrawWithContent {
					// 1️⃣ Сначала отрисовываем обычный контент (твои Box, Spacer и т.д.)
					drawContent()

					// 2️⃣ Затем рисуем поверх прямоугольник с градиентом (наш shimmer)
					// blendMode = SrcAtop означает:
					// "рисуй только там, где подложка (контент) уже непрозрачна"
					// → shimmer виден только на серых Box, а не на фоне.
					drawRect(
						brush = brush,
						blendMode = BlendMode.Color
					)
				}
			}
	) {
		// 6️⃣ Внутри Box мы просто вызываем переданный контент
		content()
	}
}