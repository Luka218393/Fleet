package com.example.fleet.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import com.example.fleet.R
import com.example.fleet.domain.Enums.ChatType
import com.example.fleet.domain.Enums.PollType
import com.example.fleet.domain.Models.Apartment
import com.example.fleet.domain.Models.Building
import com.example.fleet.domain.Models.Chat
import com.example.fleet.domain.Models.Message
import com.example.fleet.domain.Models.Notification
import com.example.fleet.domain.Models.Poll
import com.example.fleet.domain.Models.PollOption
import com.example.fleet.domain.Models.Settings
import com.example.fleet.domain.Models.SubTask
import com.example.fleet.domain.Models.Task
import com.example.fleet.domain.Models.Tenant
import com.example.fleet.domain.Models.TenantChat
import kotlinx.coroutines.runBlocking
import java.util.Date



fun seed(db: FleetDatabase){
    runBlocking{
        for (i in buildings) {
            db.buildingDao().upsert(i)
        }
        for ( i in apartments){
            db.apartmentDao().upsert(i)
        }
        for ( i in tenants){
            db.tenantDao().upsert(i)
        }
        db.settingsDao().upsert(settings1)
        for (i in notifications) {
            db.notificationDao().upsert(i)
        }
        for (i in polls){
            db.pollDao().upsert(i)
        }
        for (i in pollOptions) {
            db.pollOptionDao().upsert(i)
        }
        for (i in tasks){
            db.taskDao().upsert(i)
        }
        for (i in chats){
            db.chatDao().upsert(i)
        }
        for (i in tenantChat){
            db.tenantChatDao().upsert(i)
        }
        for (i  in messages){
            db.messageDao().upsert(i)
        }
        for (i in subTasks){
            db.subTaskDao().upsert(i)
        }
    }
}

val tenants = listOf(

    Tenant(
        id = 1,
        name = "Lux",
        apartmentId = 1,
        surname = "Æterna"
    ),
    Tenant(
        id = 2,
        name = "POkemoniiiiijisbgfvhxbfsljbvxdsdfasdsdsa",
        apartmentId = 2,
    ),
   Tenant(
        id = 3,
        name = "Luka Buka",
        apartmentId = 1,
    ),
   Tenant(
        id = 4,
        name = "Nigo",
        apartmentId = 3,
    ),
    Tenant(
        id = 5,
        name = "POkemnoo",
        apartmentId = 2,
    ),
  Tenant(
        id = 6,
        name = "YEEEEEEEEEt Shayeeet",
        apartmentId = 2,
    ),
    Tenant(
        id = 7,
        name = "Luka",
        surname = "Brčić",
        age = 18,
        apartmentId = 2,
        phoneNumber = "991247364540",//Todo fix phonwe number
        email = "zaskoluusegetu@gmail.com",
        gender = "M",
        profileImageRes = R.drawable.color_selector_image,
        birthday = Date(),
        profession = "Programmer",
        aboutMe = "My name is Jonnas I am carrying the wheel, I dont care whatt they ll say about us anywhay i dont care about that. Yut taunge is twisting your lips are slick. I dont care about that anymore",
        isOnline = false,
        isApartmentHead = true,
        isTenantLeader = true
    ),
)
 var notifications: List<Notification> = listOf(
    Notification(
        id = 1,
        title = "Notification 1",
        text = "Lorem ipsum dolor sit amet",
        iconResId = Icons.Default.Favorite,
        creatorId = 1,
        imageResId = R.drawable.lukinaikona,
        buildingId = 1,
        createdAt = Date()
    ),

    Notification(
        id = 2,
        title = "Notification 2",
        text = "Lorem ipsum dolor sit amet",
        iconResId = Icons.Default.List,
        creatorId = 1,
        buildingId = 1,
    ),
    Notification(
        id = 3,
        title = "Notification 3",
        text = "My name is bearling Lino, i eat something nice everyday, is this what life is having expectations that i+ve built foe myself. i am letting loose, loooose, driftingall away, gonna end u somewhere anywhere letting loose",
        iconResId = Icons.Default.Face,
        creatorId = 2,
        imageResId = R.drawable.flagicon,
        buildingId = 1
    )
)
var pollOptions: List<PollOption> = listOf(
    PollOption(
        id = 1,
        value = "Option 1",
        votes = listOf(),
        pollId = 1
    ),
    PollOption(
        id = 2,
        value = "Option 2",
        votes = listOf(4),
        pollId = 1
    ),
    PollOption(
        id = 3,
        value = "Option 3",
        votes = listOf(3),
        pollId = 1
    ),
    PollOption(
        id = 4,
        value = "Option 4",
        votes = listOf(),
        pollId = 1
    ),
    PollOption(
        id = 5,
        value = "Option 1",
        votes = listOf(2),
        pollId = 1
    ),
    PollOption(
        id = 6,
        value = "Da",
        votes = listOf(1),
        pollId = 2
    ),
    PollOption(
        id = 7,
        value = "Ne",
        votes = listOf(5,6),
        pollId = 2
    ),
)
var polls: List<Poll> = listOf(
    Poll(
        id = 1,
        creatorId = 1,
        title = "Why is sky blue?",
        pollType = PollType.MULTIPLE_CHOICE,
        buildingId = 1
    ),
    Poll(
        id = 2,
        creatorId = 1,
        title = "Is this poll better than the first one?",
        pollType = PollType.SINGLE_CHOICE,
        buildingId = 1
    )
)

val chats: List<Chat> = listOf(
    Chat(
        id = 1,
        title = "Luka",
        profileImageResId = R.drawable.lukinaikona,
        chatType = ChatType.TENANT_TO_TENANT
    ),
    Chat(
        id = 2,
        title = "Nina",
        profileImageResId = R.drawable.lukinaikona,
        chatType = ChatType.TENANT_TO_TENANT
    ),
    Chat(
        id = 3,
        title = "Buy new plants to decorate building",
        profileImageResId = R.drawable.lukinaikona,
        chatType = ChatType.DISCUSSION
    ),
    Chat(
        id = 4,
        title = "All tenants",
        profileImageResId = R.drawable.lukinaikona,
        chatType = ChatType.EVERYONE
    )
)

val tenantChat = listOf(
    TenantChat(
        tenantId = 1,
        chatId = 1,
        id = "1,1"
    ),
    TenantChat(
        tenantId = 1,
        chatId = 2,
        id = "1,2"
    ),
    TenantChat(
        tenantId = 2,
        chatId = 2,
        id = "2,2"
    ),
    TenantChat(
        tenantId = 2,
        chatId = 1,
        id = "2,1"
    )
)


var buildings = listOf(
    Building(
        id = 1,
        address = "1"
    ),
    Building(
        id = 2,
        address = "2"
    ),
    Building(
        id = 3,
        address = "3"
    ),
)

var apartments = listOf(
    Apartment(
        id = 1,
        buildingId = 1
    ),
    Apartment(
        id = 2,
        buildingId = 2
    ),
    Apartment(
        id = 3,
        buildingId = 1
    )

)


val settings1 = Settings(
    id = 1,
    tenantId = 1,
    apartmentId = 1,
    buildingId = 1
)



val tasks = listOf(
    Task(
        id = 1,
        title = "Task1",
        buildingId = 1,
        creatorId = 1
    ),
    Task(
        id = 2,
        title = "I am the best Task",
        buildingId = 1,
        creatorId = 2
    ),
    Task(
        id = 3,
        title = "Task from another Buiilding",
        buildingId = 2,
        creatorId = 1
    )
)

val messages = listOf(
    Message(
        id = 1,
        chatId = 1,
        senderId = 1,
        text = "WEEEEEEEEE"
    ),
    Message(
        id = 2,
        chatId = 1,
        senderId = 2,
        text = "Jonnanatourus 20000"
    ),
    Message(
        id = 3,
        chatId = 2,
        senderId = 1,
        text = "This is a veryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy looooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnngggggggggggggggggggggggg messssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssaaassggggggggggggeeeeeeeee"
    ),
    Message(
        id = 4,
        chatId = 1,
        senderId = 1,
        text = "This is a veryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy looooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnngggggggggggggggggggggggg messssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssaaassggggggggggggeeeeeeeee"
    )

)

val subTasks = listOf(
    SubTask(
        text = "Mijauuuuu",
        taskId = 1
    ),
    SubTask(
        text = "Nijauu",
        taskId = 1
    ),
    SubTask(
        text = "KFokoš",
        taskId = 1
    ),
    SubTask(
        text = "Kaktus",
        taskId = 2
    )
)