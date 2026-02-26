package quack.whattowear.network.mappers

import quack.whattowear.feature.main_screen.impl.domain.models.Address
import quack.whattowear.feature.main_screen.impl.domain.models.AddressAndForecast
import quack.whattowear.feature.main_screen.impl.domain.models.Forecast
import quack.whattowear.feature.main_screen.impl.domain.models.LabeledParameter
import quack.whattowear.feature.main_screen.impl.domain.models.PercentageParameter
import quack.whattowear.feature.main_screen.impl.domain.models.StringLabeledParameter
import quack.whattowear.feature.main_screen.impl.domain.models.Temperature
import quack.whattowear.feature.main_screen.impl.domain.models.WeatherStatus
import quack.whattowear.network.models.AddressDto
import quack.whattowear.network.models.ForecastDto
import quack.whattowear.network.models.WeatherResponse
import quack.whattowear.network.models.WindDto
import whattowear.sharedui.generated.resources.Res
import whattowear.sharedui.generated.resources.mmHg
import kotlin.math.absoluteValue

fun WeatherResponse.toDomain(): AddressAndForecast {
  return AddressAndForecast(
    address = address.toDomain(),
    forecast = forecast.toDomain()
  )
}

fun AddressDto.toDomain(): Address {
  return Address(localizedAddress = localizedTitle)
}

fun ForecastDto.toDomain(): Forecast {
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
    status = WeatherStatus.Sunny,
    predictions = emptyList()
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
