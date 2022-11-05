package net.mcreator.wdmy.procedures;

import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wdmy.WdmyMod;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class DdddProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void fovEvent(EntityViewRenderEvent.FOVModifier event){
			event.setFOV(1000);

		}
		public static void executeProcedure(Map<String, Object> dependencies) {

			if (dependencies.get("world") == null) {
				if (!dependencies.containsKey("world"))
					WdmyMod.LOGGER.warn("Failed to load dependency world for procedure Dddd!");
				return;
			}
			IWorld world = (IWorld) dependencies.get("world");
			{
				List<? extends PlayerEntity> _players = new ArrayList<>(world.getPlayers());
				for (Entity entityiterator : _players) {
					if (entityiterator.getPersistentData().getDouble("tagName") == 1) {
						if (entityiterator instanceof PlayerEntity && !entityiterator.world.isRemote()) {
							((PlayerEntity) entityiterator).sendStatusMessage(new StringTextComponent("Messageeeee"), (false));
						}
					}
				}
			}
		}

		public static void onWorldTick(TickEvent.WorldTickEvent event) {


			if (event.phase == TickEvent.Phase.END) {
				IWorld world = event.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("world", world);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}

	}


}
