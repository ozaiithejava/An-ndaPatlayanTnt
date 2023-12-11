import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.plugin.java.JavaPlugin

class InstantTNTPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        val player = event.player
        val block = event.blockPlaced

        if (block.type == Material.TNT) {
            event.isCancelled = true
            block.type = Material.AIR
            explodeTNT(player, block.location)
        }
    }

    private fun explodeTNT(player: Player, location: org.bukkit.Location) {
        player.playSound(location, Sound.ENTITY_TNT_PRIMED, 1.0f, 1.0f)
        location.world.createExplosion(location, 4.0f, true)
    }
}
