import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun sendMessage() {
        val user1 = User(1)
        val user2 = User(2)
        val expected = ChatService.sendMessage("sdssds", user1, user2)

        val result = ChatService.sendMessage("sdssds", user1, user2)

        assertEquals(expected, result)
    }

    @Test
    fun deleteChat() {
        val user1 = User(1)
        val user2 = User(2)
        ChatService.sendMessage("sdssds", user1, user2)

        val expected = ChatService.deleteChat(user1)

        val result = ChatService.deleteChat(user1)

        assertEquals(expected, result)

    }

    @Test
    fun deleteMessage() {
        val user1 = User(1)
        val user2 = User(2)
        ChatService.sendMessage("sdssds", user1, user2)


        val expected = ChatService.deleteMessage(2)

        val result = ChatService.deleteMessage(2)

        assertEquals(expected, result)
    }

    @Test
    fun editMessage() {
        val user1 = User(1)
        val user2 = User(2)
        ChatService.sendMessage("sdssds", user1, user2)

        val expected = ChatService.editMessage(2, "ura")

        val result = ChatService.editMessage(2, "ura")

        assertEquals(expected, result)

    }

    @Test
    fun getUnreadChatsCount() {
        val user1 = User(1)
        val user2 = User(2)
        ChatService.sendMessage("sdssds", user1, user2)

        val expected = ChatService.getUnreadChatsCount()

        val result = ChatService.getUnreadChatsCount()

        assertEquals(expected, result)
    }

    @Test
    fun getMessageList() {
        val user1 = User(1)
        val user2 = User(2)
        ChatService.sendMessage("sdssds", user1, user2)

        val expected = ChatService.getMessageList(1)

        val result = ChatService.getMessageList(1)

        assertEquals(expected, result)


    }
}