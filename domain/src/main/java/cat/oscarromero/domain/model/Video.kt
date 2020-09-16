package cat.oscarromero.domain.model

data class Video(val id: String, val site: Site, val type: String) {
    enum class Site {
        YOUTUBE
    }
}
