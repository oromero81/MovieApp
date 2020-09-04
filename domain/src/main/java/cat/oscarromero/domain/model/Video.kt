package cat.oscarromero.domain.model

data class Video(val id: String, val site: Site) {
    enum class Site {
        YOUTUBE
    }
}
