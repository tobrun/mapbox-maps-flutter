// This file is generated.
package com.mapbox.maps.mapbox_maps.annotation

import com.mapbox.maps.extension.style.layers.properties.generated.*
import com.mapbox.maps.mapbox_maps.toMap
import com.mapbox.maps.mapbox_maps.toPoint
import com.mapbox.maps.pigeons.FLTCircleAnnotationMessager
import com.mapbox.maps.plugin.annotation.generated.CircleAnnotation
import com.mapbox.maps.plugin.annotation.generated.CircleAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.CircleAnnotationOptions
import toCirclePitchAlignment
import toCirclePitchScale
import toCircleTranslateAnchor
import toFLTCirclePitchAlignment
import toFLTCirclePitchScale
import toFLTCircleTranslateAnchor

class CircleAnnotationController(private val delegate: ControllerDelegate) :
  FLTCircleAnnotationMessager._CircleAnnotationMessager {
  private val annotationMap = mutableMapOf<String, CircleAnnotation>()
  private val managerCreateAnnotationMap = mutableMapOf<String, MutableList<String>>()

  override fun create(
    managerId: String,
    annotationOption: FLTCircleAnnotationMessager.CircleAnnotationOptions,
    result: FLTCircleAnnotationMessager.Result<FLTCircleAnnotationMessager.CircleAnnotation>
  ) {
    try {
      val manager = delegate.getManager(managerId) as CircleAnnotationManager
      val annotation = manager.create(annotationOption.toCircleAnnotationOptions())
      annotationMap[annotation.id] = annotation
      if (managerCreateAnnotationMap[managerId].isNullOrEmpty()) {
        managerCreateAnnotationMap[managerId] = mutableListOf(annotation.id)
      } else {
        managerCreateAnnotationMap[managerId]!!.add(annotation.id)
      }
      result.success(annotation.toFLTCircleAnnotation())
    } catch (e: Exception) {
      result.error(e)
    }
  }

  override fun createMulti(
    managerId: String,
    annotationOptions: MutableList<FLTCircleAnnotationMessager.CircleAnnotationOptions>,
    result: FLTCircleAnnotationMessager.Result<MutableList<FLTCircleAnnotationMessager.CircleAnnotation>>
  ) {
    try {
      val manager = delegate.getManager(managerId) as CircleAnnotationManager
      val annotations = manager.create(annotationOptions.map { it.toCircleAnnotationOptions() })
      annotations.forEach {
        annotationMap[it.id] = it
      }
      if (managerCreateAnnotationMap[managerId].isNullOrEmpty()) {
        managerCreateAnnotationMap[managerId] = annotations.map { it.id }.toMutableList()
      } else {
        managerCreateAnnotationMap[managerId]!!.addAll(
          annotations.map { it.id }
            .toList()
        )
      }
      result.success(annotations.map { it.toFLTCircleAnnotation() }.toMutableList())
    } catch (e: Exception) {
      result.error(e)
    }
  }

  override fun update(
    managerId: String,
    annotation: FLTCircleAnnotationMessager.CircleAnnotation,
    result: FLTCircleAnnotationMessager.VoidResult
  ) {
    try {
      val manager = delegate.getManager(managerId) as CircleAnnotationManager

      if (!annotationMap.containsKey(annotation.id)) {
        result.error(Throwable("Annotation has not been added on the map: $annotation."))
        return
      }
      val originalAnnotation = updateAnnotation(annotation)

      manager.update(originalAnnotation)
      annotationMap[annotation.id] = originalAnnotation
      result.success()
    } catch (e: Exception) {
      result.error(e)
    }
  }

  override fun delete(
    managerId: String,
    annotation: FLTCircleAnnotationMessager.CircleAnnotation,
    result: FLTCircleAnnotationMessager.VoidResult
  ) {
    try {
      val manager = delegate.getManager(managerId) as CircleAnnotationManager

      if (!annotationMap.containsKey(annotation.id)) {
        result.error(Throwable("Annotation has not been added on the map: $annotation."))
        return
      }

      manager.delete(
        annotationMap[annotation.id]!!
      )
      annotationMap.remove(annotation.id)
      managerCreateAnnotationMap[managerId]?.remove(annotation.id)
      result.success()
    } catch (e: Exception) {
      result.error(e)
    }
  }

  override fun deleteAll(managerId: String, result: FLTCircleAnnotationMessager.VoidResult) {
    try {
      val manager = delegate.getManager(managerId) as CircleAnnotationManager
      managerCreateAnnotationMap[managerId]?.apply {
        forEach { annotationMap.remove(it) }
        clear()
      }
      manager.deleteAll()
      result.success()
    } catch (e: Exception) {
      result.error(e)
    }
  }

  private fun updateAnnotation(annotation: FLTCircleAnnotationMessager.CircleAnnotation): CircleAnnotation {
    val originalAnnotation = annotationMap[annotation.id]!!
    annotation.geometry?.let {
      originalAnnotation.geometry = it.toPoint()
    }
    annotation.circleSortKey?.let {
      originalAnnotation.circleSortKey = it
    }
    annotation.circleBlur?.let {
      originalAnnotation.circleBlur = it
    }
    annotation.circleColor?.let {
      originalAnnotation.circleColorInt = it.toInt()
    }
    annotation.circleOpacity?.let {
      originalAnnotation.circleOpacity = it
    }
    annotation.circleRadius?.let {
      originalAnnotation.circleRadius = it
    }
    annotation.circleStrokeColor?.let {
      originalAnnotation.circleStrokeColorInt = it.toInt()
    }
    annotation.circleStrokeOpacity?.let {
      originalAnnotation.circleStrokeOpacity = it
    }
    annotation.circleStrokeWidth?.let {
      originalAnnotation.circleStrokeWidth = it
    }
    return originalAnnotation
  }

  override fun setCircleEmissiveStrength(
    managerId: String,
    circleEmissiveStrength: Double,
    result: FLTCircleAnnotationMessager.VoidResult
  ) {
    val manager = delegate.getManager(managerId) as CircleAnnotationManager
    manager.circleEmissiveStrength = circleEmissiveStrength
    result.success()
  }

  override fun getCircleEmissiveStrength(
    managerId: String,
    result: FLTCircleAnnotationMessager.NullableResult<Double>
  ) {
    val manager = delegate.getManager(managerId) as CircleAnnotationManager
    if (manager.circleEmissiveStrength != null) {
      result.success(manager.circleEmissiveStrength!!)
    } else {
      result.success(null)
    }
  }

  override fun setCirclePitchAlignment(
    managerId: String,
    circlePitchAlignment: FLTCircleAnnotationMessager.CirclePitchAlignment,
    result: FLTCircleAnnotationMessager.VoidResult
  ) {
    val manager = delegate.getManager(managerId) as CircleAnnotationManager
    manager.circlePitchAlignment = circlePitchAlignment.toCirclePitchAlignment()
    result.success()
  }

  override fun getCirclePitchAlignment(
    managerId: String,
    result: FLTCircleAnnotationMessager.NullableResult<FLTCircleAnnotationMessager.CirclePitchAlignment>
  ) {
    val manager = delegate.getManager(managerId) as CircleAnnotationManager
    if (manager.circlePitchAlignment != null) {
      result.success(manager.circlePitchAlignment!!.toFLTCirclePitchAlignment())
    } else {
      result.success(null)
    }
  }

  override fun setCirclePitchScale(
    managerId: String,
    circlePitchScale: FLTCircleAnnotationMessager.CirclePitchScale,
    result: FLTCircleAnnotationMessager.VoidResult
  ) {
    val manager = delegate.getManager(managerId) as CircleAnnotationManager
    manager.circlePitchScale = circlePitchScale.toCirclePitchScale()
    result.success()
  }

  override fun getCirclePitchScale(
    managerId: String,
    result: FLTCircleAnnotationMessager.NullableResult<FLTCircleAnnotationMessager.CirclePitchScale>
  ) {
    val manager = delegate.getManager(managerId) as CircleAnnotationManager
    if (manager.circlePitchScale != null) {
      result.success(manager.circlePitchScale!!.toFLTCirclePitchScale())
    } else {
      result.success(null)
    }
  }

  override fun setCircleTranslate(
    managerId: String,
    circleTranslate: List<Double>,
    result: FLTCircleAnnotationMessager.VoidResult
  ) {
    val manager = delegate.getManager(managerId) as CircleAnnotationManager
    manager.circleTranslate = circleTranslate
    result.success()
  }

  override fun getCircleTranslate(
    managerId: String,
    result: FLTCircleAnnotationMessager.NullableResult<List<Double>>
  ) {
    val manager = delegate.getManager(managerId) as CircleAnnotationManager
    if (manager.circleTranslate != null) {
      result.success(manager.circleTranslate!!)
    } else {
      result.success(null)
    }
  }

  override fun setCircleTranslateAnchor(
    managerId: String,
    circleTranslateAnchor: FLTCircleAnnotationMessager.CircleTranslateAnchor,
    result: FLTCircleAnnotationMessager.VoidResult
  ) {
    val manager = delegate.getManager(managerId) as CircleAnnotationManager
    manager.circleTranslateAnchor = circleTranslateAnchor.toCircleTranslateAnchor()
    result.success()
  }

  override fun getCircleTranslateAnchor(
    managerId: String,
    result: FLTCircleAnnotationMessager.NullableResult<FLTCircleAnnotationMessager.CircleTranslateAnchor>
  ) {
    val manager = delegate.getManager(managerId) as CircleAnnotationManager
    if (manager.circleTranslateAnchor != null) {
      result.success(manager.circleTranslateAnchor!!.toFLTCircleTranslateAnchor())
    } else {
      result.success(null)
    }
  }
}

fun CircleAnnotation.toFLTCircleAnnotation(): FLTCircleAnnotationMessager.CircleAnnotation {
  val builder = FLTCircleAnnotationMessager.CircleAnnotation.Builder()
  builder.setId(this.id.toString())

  this.geometry.let {
    builder.setGeometry(it.toMap())
  }
  this.circleSortKey?.let {
    builder.setCircleSortKey(it)
  }
  this.circleBlur?.let {
    builder.setCircleBlur(it)
  }
  this.circleColorInt?.let {
    // colorInt is 32 bit and may be bigger than MAX_INT, so transfer to UInt firstly and then to Long.
    builder.setCircleColor(it.toUInt().toLong())
  }
  this.circleOpacity?.let {
    builder.setCircleOpacity(it)
  }
  this.circleRadius?.let {
    builder.setCircleRadius(it)
  }
  this.circleStrokeColorInt?.let {
    // colorInt is 32 bit and may be bigger than MAX_INT, so transfer to UInt firstly and then to Long.
    builder.setCircleStrokeColor(it.toUInt().toLong())
  }
  this.circleStrokeOpacity?.let {
    builder.setCircleStrokeOpacity(it)
  }
  this.circleStrokeWidth?.let {
    builder.setCircleStrokeWidth(it)
  }

  return builder.build()
}

fun FLTCircleAnnotationMessager.CircleAnnotationOptions.toCircleAnnotationOptions(): CircleAnnotationOptions {
  val options = CircleAnnotationOptions()
  this.geometry?.let {
    options.withPoint(it.toPoint())
  }
  this.circleSortKey?.let {
    options.withCircleSortKey(it)
  }
  this.circleBlur?.let {
    options.withCircleBlur(it)
  }
  this.circleColor?.let {
    options.withCircleColor(it.toInt())
  }
  this.circleOpacity?.let {
    options.withCircleOpacity(it)
  }
  this.circleRadius?.let {
    options.withCircleRadius(it)
  }
  this.circleStrokeColor?.let {
    options.withCircleStrokeColor(it.toInt())
  }
  this.circleStrokeOpacity?.let {
    options.withCircleStrokeOpacity(it)
  }
  this.circleStrokeWidth?.let {
    options.withCircleStrokeWidth(it)
  }
  return options
}
// End of generated file.