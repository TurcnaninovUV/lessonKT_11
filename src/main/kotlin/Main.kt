fun main() {
    val user1 = User(1)
    val user2 = User(2)
    val user3 = User(3)
    val user4 = User(4)


    ChatService.sendMessage("sdssds", user1, user2)
    ChatService.sendMessage("123", user1, user2)
    ChatService.sendMessage("098", user3, user4)
    ChatService.sendMessage ("asdasd", user3, user4)
//    ChatService.deleteChat(user1)
//    ChatService.deleteMessage(2)
//    ChatService.deleteMessage(1)
    ChatService.editMessage(2, "ura")
    println("_____________")

    ChatService.getMessageList(3)
    println("_____________")

    ChatService.getUnreadChatsCount()

    println("_____________")


}