data class Message(
        val id: Int,
        val text: String?,
        val senderId: Int,
        val recipientId: Int,
        var readOrUnread: Boolean = false
)
