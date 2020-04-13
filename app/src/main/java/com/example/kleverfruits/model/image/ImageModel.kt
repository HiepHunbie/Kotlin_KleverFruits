package com.example.kleverfruits.model.image

class ImageModel {
    private var image_drawable: Int = 0

    fun getImageDrawables(): Int {
        return image_drawable
    }

    fun setImage_drawables(image_drawable: Int) {
        this.image_drawable = image_drawable
    }
}