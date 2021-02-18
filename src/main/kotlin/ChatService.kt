object ChatService {
    private var chatMap = mutableMapOf<Chat, List<Message>>()
    private var messageList = mutableListOf<Message>()

    fun sendMessage(message: String, recipient: User, sender: User) {
        if (messageList.isEmpty()) {
            messageList.add(Message(text = message, senderId = sender.idUser,
                    recipientId = recipient.idUser, id = 1))
        } else {
            messageList.add(Message(text = message, senderId = sender.idUser,
                    recipientId = recipient.idUser, id = messageList.last().id.plus(1)))
        }
        val messageFilter = messageList.filter { messageIn ->
            messageIn.recipientId == recipient.idUser
        }
        chatMap
                .filterKeys { chatIn ->
                    chatIn.idChat == recipient.idUser
                }
                .ifEmpty {
                    val newChat = Chat(idChat = recipient.idUser)
                    chatMap.put(newChat, messageFilter)
                }
                .let {
                    chatMap.asSequence()
                            .forEach { (chatIn, list) ->
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
        var count = 0
        chatMap.forEach { message ->
            message.value.map { messageIn ->
                if (!messageIn.readOrUnread) count++
            }
        }
        println("Количество непрочитанных сообщений - $count")
    }

    fun getMessageList(idChat: Int) {
        val chatFilter = chatMap.filterKeys { it.idChat == idChat }
        chatFilter.map { message ->
            println("ID чата - ${message.key.idChat}")
            println("ID последнего сообщения - ${message.value.last().id}")
            println("Количество сообщений в чате - ${message.value.size}")
            message.value.map { messageIn ->
                messageIn.readOrUnread = true
            }
        }
    }
}