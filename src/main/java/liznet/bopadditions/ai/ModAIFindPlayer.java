package liznet.bopadditions.ai;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import liznet.bopadditions.BOPAdditions;
import liznet.bopadditions.logging.Logger;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ModAIFindPlayer extends EntityAINearestAttackableTarget<EntityPlayer>
{
    private static Method teleportRandomly = ObfuscationReflectionHelper.findMethod(EntityEnderman.class, "func_70820_n", boolean.class);
    private static Method teleportToEntity = ObfuscationReflectionHelper.findMethod(EntityEnderman.class, "func_70816_c", boolean.class, Entity.class);
	
    private final EntityEnderman enderman;
    /** The player */
    private EntityPlayer player;
    private int aggroTime;
    private int teleportTime;

    public ModAIFindPlayer(EntityEnderman entity)
    {
        super(entity, EntityPlayer.class, false);
        this.enderman = entity;
        
    	teleportRandomly.setAccessible(true);
    	teleportToEntity.setAccessible(true);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean shouldExecute()
    {
        double d0 = this.getTargetDistance();
        this.player = this.enderman.world.getNearestAttackablePlayer(this.enderman.posX, this.enderman.posY, this.enderman.posZ, d0, d0, (Function)null, new Predicate<EntityPlayer>()
        {
            public boolean apply(@Nullable EntityPlayer player)
            {
                return player != null && ModAIFindPlayer.this.shouldAttackPlayer(player);
            }
        });
        return this.player != null;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.aggroTime = 5;
        this.teleportTime = 0;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        this.player = null;
        super.resetTask();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        if (this.player != null)
        {
            if (!this.shouldAttackPlayer(this.player))
            {
                return false;
            }
            else
            {
                this.enderman.faceEntity(this.player, 10.0F, 10.0F);
                return true;
            }
        }
        else
        {
            return this.targetEntity != null && ((EntityPlayer)this.targetEntity).isEntityAlive() ? true : super.shouldContinueExecuting();
        }
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        if (this.player != null)
        {
            if (--this.aggroTime <= 0)
            {
                this.targetEntity = this.player;
                this.player = null;
                super.startExecuting();
            }
        }
        else
        {
            if (this.targetEntity != null)
            {
                if (this.shouldAttackPlayer((EntityPlayer)this.targetEntity))
                {
                    if (((EntityPlayer)this.targetEntity).getDistanceSq(this.enderman) < 16.0D)
                    {
                        this.teleportRandomly();
                    }

                    this.teleportTime = 0;
                }
                else if (((EntityPlayer)this.targetEntity).getDistanceSq(this.enderman) > 256.0D && this.teleportTime++ >= 30 && this.teleportToEntity(this.targetEntity))
                {
                    this.teleportTime = 0;
                }
            }

            super.updateTask();
        }
    }
    
    private boolean shouldAttackPlayer(EntityPlayer player)
    {
    	ItemStack itemstack = player.inventory.armorInventory.get(3);

        if (itemstack.getItem() == Item.getItemFromBlock(Blocks.PUMPKIN) || itemstack.getItem() == Item.getByNameOrId(BOPAdditions.modId + ":amethyst_helmet"))
        {
            return false;
        }
        else
        {
            Vec3d vec3d = player.getLook(1.0F).normalize();
            Vec3d vec3d1 = new Vec3d(this.enderman.posX - player.posX, this.enderman.getEntityBoundingBox().minY + (double)this.enderman.getEyeHeight() - (player.posY + (double)player.getEyeHeight()), this.enderman.posZ - player.posZ);
            double d0 = vec3d1.length();
            vec3d1 = vec3d1.normalize();
            double d1 = vec3d.dotProduct(vec3d1);
            return d1 > 1.0D - 0.025D / d0 ? player.canEntityBeSeen(this.enderman) : false;
        }
    }
    
    protected boolean teleportRandomly()
    {
    	try 
    	{
			return (boolean)ModAIFindPlayer.teleportRandomly.invoke(ModAIFindPlayer.this.enderman);
		} 
    	catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) 
    	{
			Logger.error(e);
			return false;
		}
    } 
    
    protected boolean teleportToEntity(Entity entity)
    {
    	try 
    	{
			return (boolean)ModAIFindPlayer.teleportToEntity.invoke(ModAIFindPlayer.this.enderman, entity);
		} 
    	catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) 
    	{
			Logger.error(e);
			return false;
		}
    }
}