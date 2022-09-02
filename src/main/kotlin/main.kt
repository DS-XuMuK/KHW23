data class Post(
    val id: Int = 0,
    val ownerId: Int,
    val fromId: Int = ownerId,
    val createdBy: Int = ownerId,
    val date: Int = 0,
    val text: String,
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = true,
    val comments: Any? = null,
    val copyright: Any? = null,
    val likes: Likes = Likes(0),
    val reposts: Any? = null,
    val views: Int = 0,
    val postType: String = "post",
    val postSource: Any? = null,
    val attachments: Array<Attachment>,
    val geo: Any? = null,
    val signerId: Int = ownerId,
    val copyHistory: Any? = null,
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val donut: Any? = null,
    val postponedId: Int = 0
) {
    class Likes(val count: Int, userLikes: Boolean = false)
}

object WallService {
    private var posts = emptyArray<Post>()
    private var id = 1

    fun clear() {
        posts = emptyArray()
    }

    fun add(post: Post): Post {
        posts += post.copy(id = id)
        id += 1
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, value) in posts.withIndex()) {
            if (value.id == post.id) {
                posts[index] = post.copy(ownerId = value.ownerId, date = value.date)
                return true
            }
        }
        return false
    }
}

abstract class Attachment (val type: String)

data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int,
    val text: String,
    val date: Int,
    val sizes: Any? = null,
    val width: Int,
    val height: Int
)
class PhotoAttachment(val photo: Photo) : Attachment("photo")

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    val duration: Int,
    val image: Any?,
    val firstFrame: Any?,
    val date: Int,
    val addingDate: Int,
    val views: Int,
    val localViews: Int,
    val comments: Int,
    val player: String,
    val platform: String,
    val canAdd: Boolean,
    val isPrivate: Boolean,
    val accessKey: String,
    val processing: Int,
    val isFavorite: Boolean,
    val canComment: Boolean,
    val canEdit: Boolean,
    val canLike: Boolean,
    val canRepost: Boolean,
    val canSubscribe: Boolean,
    val canAddToFaves: Boolean,
    val canAttachLink: Boolean,
    val width: Int,
    val height: Int,
    val userId: Int,
    val converting: Boolean,
    val added: Boolean,
    val isSubscribed: Boolean,
    val repeat: Int,
    val type: String,
    val balance: Int,
    val liveStatus: String,
    val live: Int,
    val upcoming: Int,
    val spectators: Int,
    val likes: Any?,
    val reposts: Any?
)
class VideoAttachment(val video: Video) : Attachment("video")

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyricsId: Int,
    val albumId: Int,
    val genreId: Int,
    val date: Int,
    val noSearch: Int,
    val isHq: Int
)
class AudioAttachment(val audio: Audio) : Attachment("audio")

data class Doc(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int,
    val ext: String,
    val url: String,
    val date: Int,
    val type: Int,
    val preview: Any? = null
)
class DocAttachment(val doc: Doc) : Attachment("doc")

data class Link(
    val url: String,
    val title: String,
    val caption: String,
    val description: String,
    val photo: Any? = null,
    val product: Any? = null,
    val button: Any? = null,
    val previewPage: String,
    val previewUrl: String
)
class LinkAttachment(val link: Link) : Attachment("link")

fun main() {
    val photo = Photo(1,1,1,1,"text", 0, null, 0, 0)
    val doc = Doc(1, 1, "title", 5, "docx", "url", 0, 1)
    val link = Link("url", "title", "caption", "description", previewPage = "page", previewUrl = "url")
    val firstPost = Post(ownerId = 44, date = 1661345460, text = "First post!", attachments = arrayOf(PhotoAttachment(photo), DocAttachment(doc)))
    val secondPost = Post(ownerId = 44, date = 1661367175, text = "Second post!", attachments = arrayOf(LinkAttachment(link), PhotoAttachment(photo)))
    val updatePost = Post(id = 2, ownerId = 44, date = 1661374009, text = "Update", attachments = arrayOf(DocAttachment(doc), LinkAttachment(link)))

    println(WallService.add(firstPost))
    println(WallService.add(secondPost))
    WallService.update(updatePost)
}