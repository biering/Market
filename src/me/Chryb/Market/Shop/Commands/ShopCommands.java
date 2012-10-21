package me.Chryb.Market.Shop.Commands;

import me.Chryb.Market.Config;
import me.Chryb.Market.Market;
import me.Chryb.Market.Shop.Owner;
import me.Chryb.Market.Shop.Shop;
import me.Chryb.Market.Shop.Shop.ShopType;
import me.Chryb.Market.Shop.Transaction;
import me.Chryb.Market.Shop.Transaction.TransactionType;
import me.Chryb.Market.Util.ChatUtil;
import me.Chryb.Market.Util.ChestUtil;
import me.Chryb.Market.Util.InvUtil;
import me.Chryb.Market.Util.MaterialUtil;
import me.Chryb.Market.Util.MessageUtil;
import me.Chryb.Market.Util.ValidationUtil;
import me.Chryb.Market.Util.MessageUtil.MessageType;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class ShopCommands implements CommandExecutor{
	
	public static Market plugin;
	public ShopCommands(Market instance){
		 plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		
		// AMOUNT CMD //
		if (cmd.getName().equalsIgnoreCase("amount") || cmd.getName().equalsIgnoreCase("a")){
		/** --------------
		* @Command: amount (shop)
		* @Usage: /amount [all:int]
		* @Description: Admit a purchase or retail.
		* @Permission: Market.cmd.amount
		--------------**/
		    if (!(sender instanceof Player)) { return false; }
			Player p = (Player)sender;
			
			if (!(Market.perm.has(p, "Market.cmd.shop.amount")) 
			 && !(Market.perm.has(p, "Market.shop.package.admin")) 
			 && !(Market.perm.has(p, "Market.shop.package.user"))){
			    ChatUtil.noPermissionMessage(p);
			    return true;
		}
			
	if (!(ValidationUtil.hasPurchase_Retail(p))){		
	    MessageUtil message = new MessageUtil(MessageType.NO_PURCHASES_RETAILS);
	    message.send(p);		
	    return true;
	}
			
	if (args.length == 1){
				if (MaterialUtil.isNumeric(args[0])){
					
					if (ValidationUtil.isRetail(p)){
						int amount = 0;
						try {
							amount = Integer.parseInt(args[0]);
						} catch(NumberFormatException nfe){
							
							MessageUtil message = new MessageUtil(MessageType.TYPE_AN_AMOUNT);
							message.send(p);
							
							return true;
						}
						TransactionType transactionType = TransactionType.RETAIL;
						Transaction transaction = new Transaction(Market.player_retail.get(p).getLocation(), transactionType, p, amount);
						transaction.run();
						return true;
					}
					if (ValidationUtil.isPurchase(p)){
						int amount = 0;
						try {
							amount = Integer.parseInt(args[0]);
						} catch(NumberFormatException nfe){
							
							MessageUtil message = new MessageUtil(MessageType.TYPE_AN_AMOUNT);
							message.send(p);
					    	  
							return true;
						}
						TransactionType transactionType = TransactionType.PURCHASE;
						Transaction transaction = new Transaction(Market.player_purchase.get(p).getLocation(), transactionType, p, amount);
						transaction.run();
						return true;
					}
					
				}
				
				if (args[0].equalsIgnoreCase("all")){
					
					if (ValidationUtil.isPurchase(p)){
						
						if (ValidationUtil.isAdminShop(Market.player_purchase.get(p).getLocation())){
							 
							MessageUtil message = new MessageUtil(MessageType.YOU_CANNOT_TYPE_AMOUNT_ALL);
							message.send(p);
					    	  
							return true; 
						}
						
						if (!(ChestUtil.hasChest(Market.player_purchase.get(p).getLocation()))){
							
							MessageUtil message = new MessageUtil(MessageType.NO_VALID_SHOP);
							message.send(p);
							
							return true;
						}
						
				MaterialData md = MaterialUtil.getBlock(Market.player_purchase.get(p).getLine(1));
				ItemStack is = new ItemStack(md.getItemType(), 1);
			        int amount = InvUtil.getItemAmount(ChestUtil.getShopChest(Market.player_purchase.get(p).getLocation()).getInventory(), is);
				Transaction transaction = new Transaction(Market.player_purchase.get(p).getLocation(), TransactionType.PURCHASE, p, amount);
				transaction.run();
				return true;
			}
					
			if (ValidationUtil.isRetail(p)){
				MaterialData md = MaterialUtil.getBlock(Market.player_retail.get(p).getLine(1));
				ItemStack is = new ItemStack(md.getItemType(), 1);
				int amount = InvUtil.getItemAmount(p.getInventory(), is);
				Transaction transaction = new Transaction(Market.player_retail.get(p).getLocation(), TransactionType.RETAIL, p, amount);
				transaction.run();
				return true;
			}
		}
				if (!(MaterialUtil.isNumeric(args[0])) && !(args[0]).equalsIgnoreCase("all") || args.length == 0){
					p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CMD_USAGE") + "/amount [x] or /a [x]");
					return true;
				}
			}
		}
		
		if (cmd.getName().equalsIgnoreCase("shop")){
			if (!(sender instanceof Player)) { return false; }
			Player p = (Player)sender;
			
			if (args.length == 0){
				
				MessageUtil message = new MessageUtil(MessageType.CMD_NOT_RECOGNIZED);
				message.send(p);
				
	            return true;
			}
			
			// HELP //
			if (args[0].equalsIgnoreCase("help")){
				
				/** --------------
				 * Command: help (shop)
				 * Usage: /shop help
				 * Description: Shows the shop help.
				 * Permission: Market.cmd.shop.help
				 --------------**/
				
				if (!(Market.perm.has(p, "Market.cmd.shop.help")) 
				 && !(Market.perm.has(p, "Market.shop.package.admin")) 
				 && !(Market.perm.has(p, "Market.shop.package.user"))){
					  ChatUtil.noPermissionMessage(p);
					  return true;
				}
				
				if (args.length == 1){
					SCommandHelper.help(p);
					return true;
				}
				if (args.length > 1){
					p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CMD_USAGE") + "/shop help");
					return true;
				}
			}
			
			// CREATE //
			if (args[0].equalsIgnoreCase("create")){
				/** --------------
				 * Command: create (shop)
				 * Usage: /shop create
				 * Description: Create a new selected Shop.
				 * Permission: Market.cmd.shop.create
				 --------------**/
				
				if (!(Market.perm.has(p, "Market.cmd.shop.create.normal")) 
				 && !(Market.perm.has(p, "Market.shop.package.admin")) 
				 && !(Market.perm.has(p, "Market.shop.package.user"))){
					  ChatUtil.noPermissionMessage(p);
					  return true;
				}
				
				if (args.length == 1){
					if (ValidationUtil.isItemFrameSelected(p)){
						Shop shop = new Shop(Market.selected_itemframe.get(p).getLocation());
						shop.create(p, ShopType.NORMAL);
						return true;
					}
					
					MessageUtil message = new MessageUtil(MessageType.NO_ITEMFRAME_SELECTED);
					message.send(p);
					
					return true;
				}
				
				if (args.length == 2){
					if (args[1].equalsIgnoreCase("Normal")){
						
						if (!(Market.perm.has(p, "Market.cmd.shop.create.normal")) 
						 && !(Market.perm.has(p, "Market.shop.package.admin")) 
						 && !(Market.perm.has(p, "Market.shop.package.user"))){
							  ChatUtil.noPermissionMessage(p);
							  return true;
						}
						
						if (ValidationUtil.isItemFrameSelected(p)){
							Shop shop = new Shop(Market.selected_itemframe.get(p).getLocation());
							shop.create(p, ShopType.NORMAL);
							return true;
						}
						
						MessageUtil message = new MessageUtil(MessageType.NO_ITEMFRAME_SELECTED);
						message.send(p);
				    	  
						return true;
					}
					
					if (args[1].equalsIgnoreCase("Admin")){
						
						if (!(Market.perm.has(p, "Market.cmd.shop.create.admin")) 
						 && !(Market.perm.has(p, "Market.shop.package.admin"))){
							  ChatUtil.noPermissionMessage(p);
							  return true;
						}
						
						if (ValidationUtil.isItemFrameSelected(p)){
							Shop shop = new Shop(Market.selected_itemframe.get(p).getLocation());
							shop.create(p, ShopType.ADMIN);
							return true;
						}
						
						MessageUtil message = new MessageUtil(MessageType.NO_ITEMFRAME_SELECTED);
						message.send(p);
				    	  
						return true;
					}
				}

				if (args.length > 2){
					p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CMD_USAGE") + "/shop create (Admin:Normal) {Selection}");
					return true;
				}
			}
			
			// DELETE //
			if (args[0].equalsIgnoreCase("delete")){
				/** --------------
				 * Command: delete (shop)
				 * Usage: /shop delete
				 * Description: Delete a selected Shop.
				 * Permission: Market.cmd.shop.delete
				 --------------**/
				
				if (!(Market.perm.has(p, "Market.cmd.shop.delete")) 
				 && !(Market.perm.has(p, "Market.shop.package.admin")) 
				 && !(Market.perm.has(p, "Market.shop.package.user"))){
					  ChatUtil.noPermissionMessage(p);
					  return true;
				}
				
				
				if (args.length == 1){
					if (ValidationUtil.isItemFrameSelected(p)){
						
						if ((Market.perm.has(p, "Market.shop.package.user")
						  || Market.perm.has(p, "Market.cmd.shop.delete"))
						&& !(Market.perm.has(p, "Market.shop.package.admin"))){
							if (!(Owner.is(p.getName(), Market.selected_itemframe.get(p).getLocation()))){
								
								MessageUtil message = new MessageUtil(MessageType.NO_SHOP_PERM);
								message.send(p);
								
								return true;
								
							}
						}
						

						Shop shop = new Shop(Market.selected_itemframe.get(p).getLocation());
						shop.delete();
						
						MessageUtil message = new MessageUtil(MessageType.SHOP_DELETE);
						message.send(p);
						
						return true;
					}
					
					MessageUtil message = new MessageUtil(MessageType.NO_ITEMFRAME_SELECTED);
					message.send(p);
			    	  
					return true;
				}

				if (args.length > 1){
					p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CMD_USAGE") + "/shop delete {Selection}");
					return true;
				}
			}
			
			// SET //
			if (args[0].equalsIgnoreCase("set")){
				
				/** --------------
				 * Command: set (shop)
				 * Usage: /shop set [purchase:retail:mode]
				 * Description: Set the shop properties.
				 * Permission: Market.cmd.shop.set
				 --------------**/
				
				// SET PURCHASE //
				if (args[1].equalsIgnoreCase("purchase")){
					
					if (!(Market.perm.has(p, "Market.cmd.shop.set.purchase")) 
					 && !(Market.perm.has(p, "Market.shop.package.admin")) 
					 && !(Market.perm.has(p, "Market.shop.package.user"))){
						  ChatUtil.noPermissionMessage(p);
						  return true;
					}
					
					if (args.length == 3){
						if (ValidationUtil.isItemFrameSelected(p)){
							
							if ((Market.perm.has(p, "Market.shop.package.user")
							  || Market.perm.has(p, "Market.cmd.shop.set.purchase"))
							&& !(Market.perm.has(p, "Market.shop.package.admin"))){
								if (!(Owner.is(p.getName(), Market.selected_itemframe.get(p).getLocation()))){
									
									MessageUtil message = new MessageUtil(MessageType.NO_SHOP_PERM);
									message.send(p);
									
									return true;
									
								}
							}

							double purchase = 0;
							try {
								purchase = Double.parseDouble(args[2]);
							} catch(NumberFormatException nfe){
								
								MessageUtil message = new MessageUtil(MessageType.TYPE_A_PRICE);
								message.send(p);
								
								return true;
							}
							Shop shop = new Shop(Market.selected_itemframe.get(p).getLocation());
							shop.setPurchase(purchase);
							Double amount = Double.parseDouble(args[2]);
							
							String shop_set_purchase = Config.getLangFile().getString("SHOP_SET_PURCHASE");
							shop_set_purchase = shop_set_purchase.replace("%amount", amount.toString());
							p.sendMessage(ChatColor.GOLD + shop_set_purchase);
							
							return true;
						}
						
						MessageUtil message = new MessageUtil(MessageType.NO_ITEMFRAME_SELECTED);
						message.send(p);
				    	  
						return true;
					}
					p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CMD_USAGE") + "/shop set purchase [Price]");
					return true;
					}
				}
				
				// SET RETAIL //
				if (args[1].equalsIgnoreCase("retail")){

					if (!(Market.perm.has(p, "Market.cmd.shop.set.retail")) 
					 && !(Market.perm.has(p, "Market.shop.package.admin")) 
					 && !(Market.perm.has(p, "Market.shop.package.user"))){
						  ChatUtil.noPermissionMessage(p);
						  return true;
					}
					
					if (args.length == 3){
						if (ValidationUtil.isItemFrameSelected(p)){
							
							if ((Market.perm.has(p, "Market.shop.package.user")
							  || Market.perm.has(p, "Market.cmd.shop.set.retail"))
							&& !(Market.perm.has(p, "Market.shop.package.admin"))){
								if (!(Owner.is(p.getName(), Market.selected_itemframe.get(p).getLocation()))){
									
									MessageUtil message = new MessageUtil(MessageType.NO_SHOP_PERM);
									message.send(p);
									
									return true;
									
								}
							}
					
							double retail = 0;
							try {
								retail = Double.parseDouble(args[2]);
							} catch(NumberFormatException nfe){
								
								MessageUtil message = new MessageUtil(MessageType.TYPE_A_PRICE);
								message.send(p);
						    	  
								return true;
							}
							Shop shop = new Shop(Market.selected_itemframe.get(p).getLocation());
							shop.setRetail(retail);
							Double amount = Double.parseDouble(args[2]);
							
							String shop_set_retail = Config.getLangFile().getString("SHOP_SET_RETAIL");
							shop_set_retail = shop_set_retail.replace("%amount", amount.toString());
							p.sendMessage(ChatColor.GOLD + shop_set_retail);
							
							return true;
						}
						
						MessageUtil message = new MessageUtil(MessageType.NO_ITEMFRAME_SELECTED);
						message.send(p);
				    	  
						return true;
					}
					if (args.length < 3 || args.length > 3){
						p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CMD_USAGE") + "/shop set retail [Price]");
						return true;
					}
				}
				
				// SET OWNER //
				if (args[1].equalsIgnoreCase("owner")){
					
					if (!(Market.perm.has(p, "Market.cmd.shop.set.owner")) 
					 && !(Market.perm.has(p, "Market.shop.package.admin"))){
						  ChatUtil.noPermissionMessage(p);
						  return true;
					}
					
					if (args.length == 3){
						if (ValidationUtil.isItemFrameSelected(p)){
							
							if ((Market.perm.has(p, "Market.shop.package.user")
							  || Market.perm.has(p, "Market.cmd.shop.set.owner"))
						    && !(Market.perm.has(p, "Market.shop.package.admin"))){
								if (!(Owner.is(p.getName(), Market.selected_itemframe.get(p).getLocation()))){
									
									MessageUtil message = new MessageUtil(MessageType.NO_SHOP_PERM);
									message.send(p);
									
									return true;
									
								}
							}
							
							Shop shop = new Shop(Market.selected_itemframe.get(p).getLocation());
							shop.setOwner(args[2]);
							String player = args[2];
							
							String shop_set_owner = Config.getLangFile().getString("SHOP_SET_OWNER");
							shop_set_owner = shop_set_owner.replace("%player", player);
							p.sendMessage(ChatColor.GOLD + shop_set_owner);
							
							return true;
						}
						
						MessageUtil message = new MessageUtil(MessageType.NO_ITEMFRAME_SELECTED);
						message.send(p);
				    	  
						return true;
					}
					if (args.length < 3 || args.length > 3){
						p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CMD_USAGE") + "/shop set owner [Player]");
						return true;
					}
				}
				
				// SET TYPE //
				if (args[1].equalsIgnoreCase("type")){
					
			if (!(Market.perm.has(p, "Market.cmd.shop.set.type")) 
					 && !(Market.perm.has(p, "Market.shop.package.admin"))){
						  ChatUtil.noPermissionMessage(p);
						  return true;
					}
					
					if (args.length == 3){
						if (ValidationUtil.isItemFrameSelected(p)){
							
							if ((Market.perm.has(p, "Market.shop.package.user")
							  || Market.perm.has(p, "Market.cmd.shop.set.type"))
						    && !(Market.perm.has(p, "Market.shop.package.admin"))){
								if (!(Owner.is(p.getName(), Market.selected_itemframe.get(p).getLocation()))){
									
									MessageUtil message = new MessageUtil(MessageType.NO_SHOP_PERM);
									message.send(p);
									
									return true;
									
								}
							}
							
							String mode = args[2];
							if (mode.equalsIgnoreCase("Admin")){
								
								Shop shop = new Shop(Market.selected_itemframe.get(p).getLocation());
								shop.setShopType(ShopType.ADMIN);
								
								MessageUtil message = new MessageUtil(MessageType.SHOP_SET_ADMIN);
								message.send(p);
						    	  
								return true;
							}
							if (mode.equalsIgnoreCase("Normal")){
								
								Shop shop = new Shop(Market.selected_itemframe.get(p).getLocation());
								shop.setShopType(ShopType.NORMAL);
								shop.setOwner("None");
								
								MessageUtil message = new MessageUtil(MessageType.SHOP_SET_NORMAL);
								message.send(p);
						    	  
								return true;
							}
							if (!(mode.equalsIgnoreCase("Admin") && (!(mode.equalsIgnoreCase("Normal"))))){
								
								MessageUtil message = new MessageUtil(MessageType.TYPE_ADMIN_NORMAL);
								message.send(p);
								
								return true;
							}
						}
						
						MessageUtil message = new MessageUtil(MessageType.NO_ITEMFRAME_SELECTED);
						message.send(p);
				    	  
						return true;
					}
					if (args.length < 3 || args.length > 3){
						p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CMD_USAGE") + "/shop set type [Admin:Normal]");
						return true;
					}
				}
			
			}
		return false;
	}

}
