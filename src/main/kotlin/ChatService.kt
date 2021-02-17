object ChatService {
    private var chatMap = mutableMapOf<Chat, List<Message>>()
    private var messageList = mutableListOf<Message>()

    fun sendMessage(message: String, recipient: User, sender: User) {
        val newMessage = if (messageList.isEmpty()) {
            Message(text = message, senderId = sender.idUser, recipientId = recipient.idUser, id = 1)
        } else {
            Message(text = message, senderId = sender.idUser,
                    recipientId = recipient.idUser, id = messageList.last().id.plus(1))
        }
        messageList.add(newMessage)

        val messageFilter = messageList.filter { messageIn ->
            messageIn.recipientId == recipient.idUser
        }
        val chatFilter = chatMap.filterKeys { chatIn ->
            chatIn.idChat == recipient.idUser
        }
        if (chatFilter.isEmpty()) {
            val newChat = Chat(idChat = recipient.idUser)
            chatMap.put(newChat, messageFilter)
        } else {
            chatMap.forEach { (chatIn, list) ->
                if (chatIn.idChat == recipient.idUser) chatMap.replace(chatIn, list, messageFilter)
            }
        }
    }

    fun deleteChat(recipient: User) {
        chatMap = chatMap.filterKeys { chats ->
            chats.idChat == recipient.idUser
        } as MutableMap<Chat, List<Message>>
    }

    fun deleteMessage(id: Int) {
        chatMap = chatMap.mapValues { message ->
            message.value.filter { it.id != id }
        } as MutableMap<Chat, List<Message>>
    }

    fun editMessage(id: Int, text: String) {
        chatMap = chatMap.mapValues { message ->
            message.value.map { messageIn ->
                if (messageIn.id == id) messageIn.copy(text = text)
                else messageIn
            }
        } as MutableMap<Chat, List<Message>>
    }

    fun getUnreadChatsCount() {
        chatMap.map { message ->
            message.value.map { messageIn ->
                if (!messageIn.readOrUnread) println(message.key)
            }
        }
    }

    fun getMessageList(idChat: Int) {
        val chatFilter = chatMap.filterKeys { it.idChat == idChat }
        chatFilter.map { message ->
            println("ID чата - ${message.key.idChat}")
            message.value.map { messageIn ->
                messageIn.readOrUnread = true
                println("ID последнего сообщения - ${message.value.last().id}")
                println("количество сообщений в чате - ${message.value.size}")
            }
        }
    }

}