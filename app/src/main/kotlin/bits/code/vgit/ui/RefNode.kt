package bits.code.vgit.ui

open class RefNode(
    @JvmField var name: String = "",
    @JvmField var parent: RefNode? = null,
)

class RefNodeParent(name: String, @JvmField var children: List<RefNode>, parent: RefNode? = null) :
    RefNode(name, parent) {

}
