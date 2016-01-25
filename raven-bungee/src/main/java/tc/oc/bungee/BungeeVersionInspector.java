package tc.oc.bungee;

import java.util.Collection;
import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import tc.oc.minecraft.VersionInspector;

public class BungeeVersionInspector implements VersionInspector {

    private final ProxyServer server;

    public BungeeVersionInspector(ProxyServer server) {
        this.server = server;
    }

    @Override
    public String getServerName() {
        return server.getName();
    }

    @Override
    public String getServerVersion() {
        return server.getVersion();
    }

    @Override
    public Collection<String> getPluginNames() {
        return Collections2.transform(server.getPluginManager().getPlugins(), new Function<Plugin, String>() {
            @Override public String apply(@Nullable Plugin plugin) {
                return plugin.getDescription().getName();
            }
        });
    }

    @Override
    public String getPluginVersion(String pluginName) {
        final Plugin plugin = server.getPluginManager().getPlugin(pluginName);
        if(plugin == null) throw new IllegalArgumentException("No plugin named " + pluginName);
        return plugin.getDescription().getVersion();
    }
}
