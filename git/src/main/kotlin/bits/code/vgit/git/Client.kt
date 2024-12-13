package bits.code.vgit.git

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.Ref
import org.eclipse.jgit.revwalk.RevCommit
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import java.io.File

object Client {

    private val repository =
        FileRepositoryBuilder().setGitDir(File("C:\\Users\\Admin\\Workspace\\javascript\\Recoil\\.git")).readEnvironment()
            .findGitDir().build()

    private val git = Git(repository)

    fun commits(): List<RevCommit> {
        return git.log().call().toList()
    }

    fun branches(): List<Ref> {
        return git.branchList().call()
    }
}