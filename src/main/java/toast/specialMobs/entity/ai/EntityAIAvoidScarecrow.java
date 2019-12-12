package toast.specialMobs.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import toast.specialMobs.block.ScarecreeperTileEntity;

import java.util.Random;
import java.util.TreeMap;

/**
 * Created by jtidwell on 11/22/2015.
 */
public class EntityAIAvoidScarecrow extends EntityAIBase{

    private EntityCreature theEntity;
    private World theWorld;

    private Block scareCrowBlock;
    private Double[] nearestScarecrow;
    private int radarRange;

    /** The PathEntity of our entity */
    private PathEntity entityPathEntity;
    /** The PathNavigate of our entity */
    private PathNavigate entityPathNavigate;

    private double farSpeed;
    private double nearSpeed;
    private int farRange;
    private int nearRange;

    public EntityAIAvoidScarecrow(EntityCreature entity, Block scareCrowBlock, int radarRange,
                                  double nearSpeed, double farSpeed, int nearRange, int farRange) {
        theEntity = entity;
        theWorld = entity.worldObj;
        entityPathNavigate = entity.getNavigator();
        this.radarRange = radarRange;
        this.scareCrowBlock = scareCrowBlock;
        this.nearSpeed = nearSpeed;
        this.farSpeed = farSpeed;
        this.nearRange = nearRange;
        this.farRange = farRange;
        setMutexBits(7);
    }

    @Override
    public boolean shouldExecute() {

        scanForScarecrow();

        if(nearestScarecrow == null) {
            return false;
        }

        theWorld.spawnParticle("splash",
                theEntity.posX + (theWorld.rand.nextDouble() - 0.5) * theEntity.width,
                theEntity.posY + theWorld.rand.nextDouble() * theEntity.height - 0.25,
                theEntity.posZ + (theWorld.rand.nextDouble() - 0.5) * theEntity.width,
                (theWorld.rand.nextDouble() - 0.5) * 2.0,
                -theWorld.rand.nextDouble(),
                (theWorld.rand.nextDouble() - 0.5) * 2.0);

        Vec3 vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(
                this.theEntity, farRange, nearRange,
                Vec3.createVectorHelper(nearestScarecrow[0], nearestScarecrow[1], nearestScarecrow[2]));

        if (vec3 == null)
        {
            return false;
        }

        this.entityPathEntity = this.entityPathNavigate.getPathToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord);
        return this.entityPathEntity == null ? false : this.entityPathEntity.isDestinationSame(vec3);

    }

    private void scanForScarecrow() {

        int bottom = radarRange * -1;
        int top = radarRange + 1;

        TreeMap<Double, Double[]> radar = new TreeMap<Double, Double[]>();

        for(int i = bottom; i < top; i++) {
            for (int j = bottom; j < top; j++) {
                for (int k = bottom; k < top; k++) {
                    Block targetBlock = theWorld.getBlock(
                            (int)(theEntity.posX + i),
                            (int)(theEntity.posY + j),
                            (int)(theEntity.posZ + k));
                    if(targetBlock.getClass() == scareCrowBlock.getClass()) {

                        double xc = theEntity.posX + (double)i;
                        double yc = theEntity.posY + (double)j;
                        double zc = theEntity.posZ + (double)k;

                        Double distance = theEntity.getDistance(xc, yc, zc);
                        Double[] coords = new Double[3];
                        coords[0] = xc;
                        coords[1] = yc;
                        coords[2] = zc;

                        TileEntity te = theWorld.getTileEntity((int)(theEntity.posX + i),
                                (int)(theEntity.posY + j),
                                (int)(theEntity.posZ + k));
                        if(theEntity instanceof EntityCreeper && te instanceof ScarecreeperTileEntity &&
                                ((ScarecreeperTileEntity)te).getBurning() == false) {
                            continue;
                        }
                        radar.put(distance, coords);
                    }

                }
            }
        }


        if(radar.keySet().size() > 0) {
            nearestScarecrow = radar.get(radar.firstKey());
        }
        else {
            nearestScarecrow = null;
        }

    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.entityPathNavigate.noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.entityPathNavigate.setPath(this.entityPathEntity, this.farSpeed);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.nearestScarecrow = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (this.theEntity.getDistance(
                this.nearestScarecrow[0],
                this.nearestScarecrow[1],
                this.nearestScarecrow[2]) < 49.0D)
        {
            this.theEntity.getNavigator().setSpeed(this.nearSpeed);
        }
        else
        {
            this.theEntity.getNavigator().setSpeed(this.farSpeed);
        }

        Random rand = new Random();
        WorldServer worldServer = (WorldServer) theEntity.worldObj;
        worldServer.func_147487_a("splash",
                theEntity.posX + (rand.nextDouble() - 0.5D) * (double)theEntity.width,
                theEntity.posY + rand.nextDouble() * (double)theEntity.height - 0.25D,
                theEntity.posZ + (rand.nextDouble() - 0.5D) * (double)theEntity.width,
                5, 0.0D, 0.0D, 0.0D, 0.0D);
    }
}
