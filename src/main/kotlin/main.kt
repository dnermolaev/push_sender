import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val message = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
          "userId": 1,
          "userName": "Dmitry",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken("e6WOHK2zQIKlRSES1DBo52:APA91bH6-crqpikpRAN_tSsghMZYvpuvIdptFVJmTb0jqyIM8K8a-HBLdQn9KMCl0nNmH-Kkhd17KOV2eeMyKqJMWQm8w4gCMS34y7xXsANvpETdB4Cj91m6HX58ly27FI0xfgG4i3gH")
        .build()

    val newPostMessage = Message.builder()
        .putData("action", "POST")
        .putData("content", """{
          "id": 1,
          "author": "Dmitry",
          "content": "This is a new post about making notifications",
          "published": "Now"
        }""".trimIndent())
        .setToken("e6WOHK2zQIKlRSES1DBo52:APA91bH6-crqpikpRAN_tSsghMZYvpuvIdptFVJmTb0jqyIM8K8a-HBLdQn9KMCl0nNmH-Kkhd17KOV2eeMyKqJMWQm8w4gCMS34y7xXsANvpETdB4Cj91m6HX58ly27FI0xfgG4i3gH")
        .build()

    FirebaseMessaging.getInstance().send(newPostMessage)
}