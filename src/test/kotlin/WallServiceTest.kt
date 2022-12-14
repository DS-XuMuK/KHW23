import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val doc = Doc(1, 1, "title", 5, "docx", "url", 0, 1)
        val post = Post(ownerId = 1, text = "text", attachments = arrayOf(DocAttachment(doc)))
        val result = WallService.add(post)
        assertNotEquals(0, result.id)
    }

    @Test
    fun updateIdExists() {
        val doc = Doc(1, 1, "title", 5, "docx", "url", 0, 1)
        val post = Post(ownerId = 1, text = "text", attachments = arrayOf(DocAttachment(doc)))

        val updatePost =
            Post(ownerId = 1, text = "update", id = WallService.add(post).id, attachments = arrayOf(DocAttachment(doc)))
        val result = WallService.update(updatePost)
        assertTrue(result)
    }

    @Test
    fun updateIdNotExists() {
        val doc = Doc(1, 1, "title", 5, "docx", "url", 0, 1)
        val post = Post(ownerId = 1, text = "text", id = Int.MAX_VALUE, attachments = arrayOf(DocAttachment(doc)))
        val result = WallService.update(post)
        assertFalse(result)
    }
}