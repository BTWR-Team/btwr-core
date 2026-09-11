## UNFINISHED:
+ [dev] Added/backported the difficulty system from BTW: Community Edition! By default, it contains the base difficulties (classic, relaxed, standard & hostile), but it also allows addon developers to use the API to add custom difficulties too

## v0.5
### Warning, this is a game-breaking update. Proceed with caution!
+ Added "Hardcore Buoy". It changes vanilla buoyancy for items touching water to act differently based on their buoyancy value. Customizable with item tags and can be turned off in the config options
+ Added "Hardcore Poking" (Only sticks placeable as blocks, tools being placeable are already in the Bind mod)
+ Added "Hardcore Player Names" as a config option. Allows hiding of player name tags on SMP
+ Added a custom data-driven system for defining which conditions mobs can spawn in the world
+ Added/removed invulnerability frames for players when joining a game. This applies only on integrated servers and is configurable
+ Added optional recipes for crafting diamond tools with diamond ingots.
+ Changed the diamond ingots default recipe to yield two diamond ingots. This gives an incentive for using diamond ingots over diamonds
+ Changed diamond ingots recipe to be crafted with iron ingot, diamond, rotten flesh and red mushroom. This is the placeholder recipe for the mod since there are no creeper oysters anymore
+ Changed lightning strikes to be two times as common when it's thundering. This can be turned off with the new configuration option `increasedLightningStrikeChance`
+ Fixed knockback not applying in certain cases with the knockback restrictions rule
+ Removed all the new food items/combinations and moved them to the Self Sustainable mod as it made most sense for them to be there
+ Removed creeper shearing and creeper oysters. Those are now in a new separate mod called "Creeper Shearing"
+ Removed/reworked the config option for "spawnMobsOnWood" and made it use the new data-driven mob spawn system. The name of the configuration option is now "btwMobSpawningLogic"
+ Updated the mod license in the source code to show correct information
+ Updated the mod to Fabric API 0.116.15, Fabric Loader 0.19.5 & BTWR: Shared Library 0.8.5

## v0.4.1
+ Fixed missing spider eye blocks and slab from the creative inventory tab
+ Fixed creepers dropping creeper oysters even when neutered after killed

## v0.4
+ The mod is officially in Beta now!
+ Added new blocks:
  - Block of Flint
  - Block of Diamond Ingot
  - Block of Creeper Oyster (and slab)
  - Block of Spider Eye (and slab)
+ Added blight!
+ Added a new api class `BlightSpreadConditions`. This can be used by developers who want to add compatibility for their custom blocks and define conditions under which blight can spread to a these blocks
+ Added new items Ocular of Ender and Ender Spectacles! Ender spectacles are now used in place of carved pumpkins to not aggro enderman when looking at them and also show positions where mobs can spawn if it's dark enough
+ Added head drops for players and certain vanilla mobs when killed. Normally this only scales with Looting, but it has compatibility for Better With Time's Battle Axe and also for BWT: Hardcore Tweaks's chopping block killing mechanic
+ Added custom sound events for creeper shearing!
+ Changed the diamond block texture to resemble more that of a diamond gem block (This is necessary since the diamond ingot block uses the vanilla one)
+ Changed the recipe for diamond plates to yield four plates instead of one to have an incentive to use them instead of normal diamonds
+ Changed how shears are applied as a correct tool to drop leaves blocks internally to allow for other modifications to happen smoother instead of just modifying leaves blocks loot tables
+ Changed the mod versioning scheme to [semver](https://semver.org/).
+ Updated the mod to Fabric API 0.116.11, Fabric Loader 0.19.2 & BTWR: Shared Library 0.8.4

## v0.33.5
+ Fixed tanned leather armor pieces having wrong durability values
+ General code cleanup and moved some classes to BTWR: Shared Library
+ Updated the mod to BTWR: Shared Library 0.8

## v0.33.4
+ Updated the mod to BTWR: Shared Library 0.7

## v0.33.3
+ Fixed a bug that would cause the mod to crash when launching the game

## v0.33.2
+ Changed all configuration options in the mod to use the custom config library added by BTWR: Shared Library. This fixes the bug from last version that crashed the game without any warnings of the missing library that created the configurations
+ Removed Supermartijn642's config lib as the one creating configuration setting as it requires itself as a dependency to work properly
+ Updated the mod to BTWR: Shared Library 0.6.5

## v0.33.1
+ Fixed the server configuration options which I tried adding to Mod Menu since the previous update to only be accessible through modifying the config .toml file manually because they weren't working properly. While this might be theoretically possible I'm not sure of the technical details, so changing config options through mod menu for local worlds is not available yet
+ Updated the mod to BTWR: Shared Library 0.6.4

## v0.33
+ Added a new mod tag "NEUTERABLE_CREEPERS" which specifies which creeper entity types can be sheared. Developers can add their custom creepers to this tag to make them shearable.
+ Added a new class "NeuteredCreeperTextures" which allows developers to add sheared texture variants for their custom creeper.
+ Changed/Generally improved Mod Menu configuration option screens to better show how configuration options work
+ Changed all configuration options setting to be handled with Supermartijn642's Config Lib internally instead of Cloth Config API, which is used only for client side config options. Cloth Config is still used for creating all screens for access through Mod Menu.
+ Changed the creeper shearing modification to use Fabric Data Attachments instead of only using mixins.
+ Refactored pretty much the whole code; mainly for readability and cleaning up, but also so it's more in order with other mods from the BTWR project
+ Updated the mod to BTWR: Shared Library 0.63

## v0.32
+ Changed Hearty Stew's class internally, so existing Hearty Stew items might disappear from your worlds
+ Added recipe for crafting Diamond Plate
+ Added recipes for crafting diamond armor with Diamond Ingots and Diamond Plate
+ Fixed Chowder not giving back a bowl after being used
+ Fixed Tanned Leather armor not being enchantable
+ Updated the mod to Fabric Loader 0.17.3 & BTWR: Shared Library 0.62

## v0.31.4
+ Updated ExtendedShapelessRecipe from BTWR: Shared Library, which fixes some bugs with recipes using it
+ Updated the mod to Fabric API 0.116.7 & BTWR: Shared Library 0.61

## v0.31.3
+ Updated mod recipes to use the new ExtendedShapelessRecipe from BTWR: SL v0.60
+ Split mod client side from the main package. Although there weren't any problems before, this is a good practice and the mod should work better in multiplayer
+ Removed old unused assets from the mod files and cleaned up source code a lot
+ Updated the mod to BTWR: Shared Library 0.60

## v0.31.2
+ Removed the remainder logic that damages shears and axes when crafting and moved it to the BTWR: Shared library mod.
+ Updated the mod to Fabric API 0.116.6, Fabric Loader 0.17.2 & BTWR: Shared Library 0.58

## v0.31.1
+ Reverted the hoe modification code from the last update and moved it to an unreleased mod. 
It was causing some issues with other mods that need to be cleaned up before release and
also it's more of a hardcore modification, so I'd like to keep it only in the BTWR modpack.

## v0.31
+ Added a new configurable option that changes how hoes work. Normal usage with right click is removed and players must use the old school way of breaking grass with left click.
+ Added tooltips for all config options when looking at them in Mod Menu
+ Changed creeper explosion to be calculated from their eyes instead feet (also made it toggleable feature via mod menu)
+ Fixed a bug where Hearty Stew & Chicken Soup would not give back an empty bowl item when finished using it if the player inventory is full.
+ Fixed incompatibility with mods that add custom creepers to not get affected by the creeper shearing and ruin their intended experience.
+ Updated the mod to Fabric API 0.116.0, Fabric Loader 0.16.14 & BTWR: Shared Library 0.53

## v0.30.1
+ Fixed a bug with older data files from version 0.29 that were causing issues.

## v0.30
### WARNING! Game breaking update, back up your worlds if you don't want to lose some items/blocks.
+ Removed the Hemp plant block and all of its associated items as it's no longer needed in the scope of this mod. They will be removed from your worlds.
+ Moved the "Brick Drying" functionality to another unreleased mod. This includes the Wet Brick & Brick blocks & items, which means they will also be removed from your worlds.
+ Moved the SHEARS_EFFICIENT block tag to BTWR-SL mod
+ Fixed some mixin related bugs on mod launch with other mods requiring older versions of BTWR Shared Library
+ Updated the mod to Fabric API 0.115.0, Fabric Loader 0.16.10 & BTWR: Shared Library 0.47

## 0.29
+ Added a new common block tag "WOODEN_MISC_BLOCKS" for miscellaneous blocks that aren't tagged as wooden,
but should be treated as such. This was created especially for the "Don't spawn mobs on wood" config option,
but can be used by other purposes eventually.

+ Made vine blocks harvestable with Diamond shears.
+ Changed the efficiency conditions for shears tools and added a specific SHEARS_EFFICIENT block tag.
+ Fixed Shears & Diamond Shears not making a crafting sound when crafted.
+ Fixed a bug where certain items that should have a returning stack on crafting them with recipes to
properly return the item used (like buckets, axes in recipes, etc.)
+ Fixed mod description to display properly in the mod list.
+ Fixed the mod's license to show properly (changed from MIT to CC-BY 4.0)
+ Removed the Cooked Carrot and Boiled Potato items (moved them to an unreleased mod)
+ Removed the default 16 item count for the newly added food items (back to default 64)
+ Updated the mod to BTWR-SL 0.44

## 0.28.2
+ Added the ability to make mobs unable to spawn on all types of wood blocks. This can be disabled in the config.
+ Added config option to make spawn capacity of monsters per chunk slightly higher. This can be disabled in the config.

+ Added Diamond Shears to the Conventional Item Tags SHEAR TOOLS
+ Added Shears cutting sound for crafting Cut Leather recipes.
+ Moved shear recipes to use the conventional item tag SHEAR_TOOLS.
+ Fixed the names of tanned and scoured cut leather items being swapped.
+ Improved the naming of switchable options in the config file and the configuration menu through Mod Menu.
+ Updated the project to Fabric API 0.114.0 && to require BTWR: Shared Library 0.40.

### 0.28.1
+ Made the mod (actually) depend on BTWR- Shared Library mod
+ Fixed a bug that caused the game to crash when a block was updating the Brick-Placed block.
+ Moved the COOKED_MEATS_FOR_SANDWICH item tag to BTWRConventionalTags in BTWR Shared Library
+ Changed Hearty Stew to use the COOKED MEAT FOODS Conventional Item tag

## 0.28
+ Enabled (fixed) the ability to knockback entities without the proper weapon if the player is sprinting
+ Fixed a bug that crashed the game when attacking silverfish
+ Fixed being able to melee knockback entities with crossbow or bow while knockback restrictions are enabled.
+ Fixed a server side bug that caused breaking of directional dropping blocks to crash the client.
+ Fixed Shear tools from being too slow in breaking Hemp.
+ Fixed placement sounds for some brick blocks
+ Code refactoring/improvements
+ Removed the following items because  they have no use in the scope of this mod:
  Filament, Gear, Belt, Strap, Padding, Rope, Stone Brick -> (moved to Tough Environment).
+ Removed LightBlock and Rope Coil block for the same reason
+ Removal of the LightBlock also fixed a bug that caused its item to get registered twice and crashed the client on connecting to dedicated servers.
+ Reverted the name change of Stick to Shaft

+ Updated the mod to Fabric API 0.110.0 & Fabric Loader 0.16.9

## 0.27

+ Added new food items:
1. Ham and Eggs
2. Scrambled Eggs (raw and cooked)
3. Mushroom Omelette (raw and cooked)
4. Chowder
5. Steak and Potatoes
6. Kebab (raw and cooked)
7. Steak, Pork & Wolf Dinner Stews (Wolf dinner unacquirable because no wolf meat)
8. Chicken Soup & Hearty Stew

+ Changed the newly added food items max stack limit to 16

## 0.26.1

+ Fixed a bug where breaking leaves with Diamond Shears didn't drop the block & another one where it was breaking them very slow.
+ Conventional tags changes;
  added new gears item tag for all mechanical gear items that are interchangeable for btwr ones.
  moved hemp fibers to the "strings" conventional item tag from fabric.
+ Updated the mod to Fabric Loader 0.16.7 & Fabric API 0.107.0

## 0.26

+ Slightly reworked directional drop logic so items should drop better than before.
+ Updated the mod to Fabric Loader 0.16.5 & Fabric API 0.104.0

## 0.25.3

+ Fixed Diamond Shears breaking leaves slower than iron ones & fixed some leaves loot tables to drop properly.
+ Fixed a bug where you could do knockback with a bow or crossbow from melee hits to entities.

## 0.25.2

+ Fixed a bug with custom shapeless recipes that have additional drops to drop properly.
+ Fixed placement code for bricks items as blocks to work much better(and as intended).
+ Fixed missing recipes and generally improved the code that generates them.

## 0.25.1

+ Updated 0.25.1 to latest minecraft versions

## 0.25

+ Added sandwich food item.
+ Removed recipe for cooking brick on campfires.
+ Changed water buckets to place non-persistent water, instead of source blocks.
+ Changed waterlogged blocks to dissipate their water when broken.
+ Optimized directional dropping code for mods that support it (like Sturdy Trees & Tough Environment)
+ Fixed code to work properly on the server side.

## 0.24

+ Added Unfired Brick item & block & the ability to sun dry them into regular bricks.
+ Added Diamond Armor Plate item and changed recipes for making diamond armor.
+ Added Fuel values to all "flammable" items like wooden club, gear and all hemp derivatives.
+ Added recipes for cut leather to be used with some existing recipes that require normal leather.
+ Added missing recipe for making Strap.
+ Rebalanced Shears block breaking speed.
+ Made the Brick item placeable in the world as a block.
+ Fixed a bug with creepers not igniting with fire charge.
+ Fixed Strap recipe only giving one strap instead of 4
+ Changes to BTWR conventional tags.

## 0.23.2

+ Fixed a bug with axes getting consumed on crafting with them.
+ Updated the mod to Fabric Loader 0.15.11

## v0.23.1

+ Added Tanned Leather Armor.
+ Added Recipes for acquiring Scoured & Tanned Leather.
+ Increased Shears mining speed for effective blocks.

+ Updated the mod to Fabric Loader 0.15.10


## v0.23

I decided that by default this mod will not change the balance of the game.
Most of that will happen in the BTWR Modpack itself.

+ ### Added the following items/blocks:
  - Gear
  - Belt
  - Strap
  - Stone Brick
  - Hemp Crop, Hemp Fibers & Hemp Fabric
  - Light Block
  - Rope
  - Rope Coil
  - Scoured & Tanned leather
  - Cut Leather for all 3 variations (normal, scoured, tanned)

+ ### Added some Config options toggles through Mod Menu:
  - For disabling Baby Zombies spawning.
  - For knockback restrictions (needing a weapon item to do knockback to entities)
  

+ Added Custom Conventional tags to handle interactions between the sidemods better.

* Fixed being unable to shear sheep with Diamond Shears.

+ Brought back Wooden & Stone sword recipes.
+ Restored vanilla mining speeds & durability of all tools.
+ Updated lang file to modify name of Stick to Shaft.

## v0.22

+ Added Wooden & Bone Club weapons.
+ Added Creeper Oysters Item.
+ Added Diamond Ingot Item.
+ Added Diamond Shears Item.
+ Added Sounds to certain item when they are crafted for extra immersion. :)

+ Changed monsters and animals to no longer take knockback if you aren't using a weapon item. (Club, Sword, Bow, Crossbow && Trident)
+ Changed how creepers work. You can now neuter them by right-clicking on them with Shears.
+ Changed the durability and mining speed of all tools. This makes getting iron tools(& above) much more useful.
+ Changed name of Stick to Shaft.

+ Removed Wooden & Stone Sword.

+ Brought back Wooden & Stone Sword recipes.
+ Restored vanilla mining speeds & durability of all tools.
+ Restored the ability to knockback with any weapon.

+ # Release Alpha

+ Initial public release
+ Versions before 0.22 were private testing only.

