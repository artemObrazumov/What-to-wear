package quack.whattowear.network.mappers

import quack.whattowear.feature.main_screen.impl.domain.models.Address
import quack.whattowear.feature.main_screen.impl.domain.models.AddressAndForecast
import quack.whattowear.feature.main_screen.impl.domain.models.ClothesAdvice
import quack.whattowear.feature.main_screen.impl.domain.models.Forecast
import quack.whattowear.feature.main_screen.impl.domain.models.LabeledParameter
import quack.whattowear.feature.main_screen.impl.domain.models.PercentageParameter
import quack.whattowear.feature.main_screen.impl.domain.models.Prediction
import quack.whattowear.feature.main_screen.impl.domain.models.StringLabeledParameter
import quack.whattowear.feature.main_screen.impl.domain.models.Temperature
import quack.whattowear.feature.main_screen.impl.domain.models.WeatherStatus
import quack.whattowear.feature.main_screen.impl.domain.models.toWeatherStatus
import quack.whattowear.network.models.AddressDto
import quack.whattowear.network.models.AiResponse
import quack.whattowear.network.models.ForecastDto
import quack.whattowear.network.models.ForecastItemDto
import quack.whattowear.network.models.ForecastResponse
import quack.whattowear.network.models.WeatherResponse
import quack.whattowear.network.models.WindDto
import whattowear.sharedui.generated.resources.Res
import whattowear.sharedui.generated.resources.mmHg
import kotlin.math.absoluteValue

fun WeatherResponse.toDomain(forecastResponse: ForecastResponse): AddressAndForecast {
  return AddressAndForecast(
    address = address.toDomain(),
    forecast = forecast.toDomain(forecastResponse.toDomain())
  )
}

fun AddressDto.toDomain(): Address {
  return Address(localizedAddress = localizedTitle)
}

fun ForecastDto.toDomain(predictions: List<Prediction>): Forecast {
  val temperatureValue = temperature.toInt()
  val feelsLikeTemperatureValue = temperatureFeelsLike.toInt()

  return Forecast(
    temperature = Temperature(
      value = temperatureValue.absoluteValue,
      sign = temperatureValue.toSign()
    ),
    feelsLikeTemperature = Temperature(
      value = feelsLikeTemperatureValue.absoluteValue,
      sign = feelsLikeTemperatureValue.toSign()
    ),
    wind = wind.toDomain(),
    humidity = PercentageParameter(humidity),
    pressure = LabeledParameter(Res.string.mmHg, pressure),
    status = WeatherStatus.SUNNY,
    predictions = predictions
  )
}

fun ForecastResponse.toDomain(): List<Prediction> {
  return predictions.map { it.toDomain() }
}

fun AiResponse.toDomain(): ClothesAdvice {
  return ClothesAdvice(
    headwear = headwear,
    outerwear = outerwear,
    upperBodyClothing = upperBodyClothing,
    lowerBodyClothing = lowerBodyClothing,
    footwear = footwear,
    accessories = accessories,
  )
}

fun ForecastItemDto.toDomain(): Prediction {
  val temperatureValue = temperature.toInt()
  val timeOnly = time.split(" ")[1]
  return Prediction(
    weatherStatus = status.toWeatherStatus(),
    temperature = Temperature(
      value = temperatureValue.absoluteValue,
      sign = temperatureValue.toSign()
    ),
    time = timeOnly,
  )
}

fun WindDto.toDomain(): StringLabeledParameter {
  return StringLabeledParameter(
    label = direction,
    value = speed.toInt()
  )
}

fun Int.toSign(): Temperature.Sign {
  return when {
    this > 0 -> Temperature.Sign.PLUS
    this < 0 -> Temperature.Sign.MINUS
    else -> Temperature.Sign.EMPTY
  }
}
